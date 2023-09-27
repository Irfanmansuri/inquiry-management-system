package com.ims.views;

import org.vaadin.lineawesome.LineAwesomeIcon;

import com.ims.security.AuthenticatedUser;
import com.ims.views.about.AboutView;
import com.ims.views.dashboard.DashboardView;
import com.ims.views.inquiry.InquiryView;
import com.ims.views.login.LoginView;
import com.ims.views.login.SingupView;
import com.ims.views.message.MessageView;
import com.ims.views.organization.OrganizationView;
import com.ims.views.products.ProductsView;
import com.ims.views.subproducts.SubProductsView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

	private H2 viewTitle;

	private AuthenticatedUser authenticatedUser;
	private AccessAnnotationChecker accessChecker;

	public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
		this.authenticatedUser = authenticatedUser;
		this.accessChecker = accessChecker;
		createHeader();
		setPrimarySection(Section.NAVBAR);
		addDrawerContent();
//        addHeaderContent();
	}

	private void addHeaderContent() {
		DrawerToggle toggle = new DrawerToggle();
		toggle.setAriaLabel("Menu toggle");

		viewTitle = new H2();
		viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

		addToNavbar(true, toggle, viewTitle);
	}

	private void addDrawerContent() {
		H1 appName = new H1("Inquiry Management System");
		appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
		Header header = new Header(appName);

		Scroller scroller = new Scroller(createNavigation());

		addToDrawer(header, scroller, createFooter());
	}

	private SideNav createNavigation() {
		SideNav nav = new SideNav();

		if (accessChecker.hasAccess(DashboardView.class)) {
			nav.addItem(new SideNavItem("Dashboard", DashboardView.class, LineAwesomeIcon.CHART_AREA_SOLID.create()));

		}
		if (accessChecker.hasAccess(ProductsView.class)) {
			nav.addItem(new SideNavItem("Products", ProductsView.class, LineAwesomeIcon.FILTER_SOLID.create()));

		}
		if (accessChecker.hasAccess(SubProductsView.class)) {
			nav.addItem(new SideNavItem("Sub Products", SubProductsView.class, LineAwesomeIcon.FILTER_SOLID.create()));

		}
		if (accessChecker.hasAccess(MessageView.class)) {
			nav.addItem(new SideNavItem("Message/Marketing", MessageView.class, LineAwesomeIcon.ENVELOPE.create()));

		}
		if (accessChecker.hasAccess(InquiryView.class)) {
			nav.addItem(new SideNavItem("Inquiry", InquiryView.class, LineAwesomeIcon.MOBILE_ALT_SOLID.create()));

		}
		if (accessChecker.hasAccess(OrganizationView.class)) {
			nav.addItem(new SideNavItem("Organization", OrganizationView.class, VaadinIcon.OFFICE.create()));
			
		}
		if (accessChecker.hasAccess(LoginView.class)) {
			nav.addItem(new SideNavItem("Login", LoginView.class, LineAwesomeIcon.USER.create()));

		}
		if (accessChecker.hasAccess(SingupView.class)) {
			nav.addItem(new SideNavItem("Singup", SingupView.class, LineAwesomeIcon.USER.create()));

		}
		if (accessChecker.hasAccess(AboutView.class)) {
			nav.addItem(new SideNavItem("About", AboutView.class, LineAwesomeIcon.FILE.create()));

		}

		return nav;
	}

	private Footer createFooter() {
		Footer layout = new Footer();
		layout.setText("Powered and managed by IQRA GROUP");
		layout.getStyle().setBackground("green").setColor("white");

		/*
		 * Optional<User> maybeUser = authenticatedUser.get(); if
		 * (maybeUser.isPresent()) { User user = maybeUser.get();
		 * 
		 * Avatar avatar = new Avatar(user.getName()); StreamResource resource = new
		 * StreamResource("profile-pic", () -> new
		 * ByteArrayInputStream(user.getProfilePicture()));
		 * avatar.setImageResource(resource); avatar.setThemeName("xsmall");
		 * avatar.getElement().setAttribute("tabindex", "-1");
		 * 
		 * MenuBar userMenu = new MenuBar();
		 * userMenu.setThemeName("tertiary-inline contrast");
		 * 
		 * MenuItem userName = userMenu.addItem(""); Div div = new Div();
		 * div.add(avatar); div.add(user.getName()); div.add(new Icon("lumo",
		 * "dropdown")); div.getElement().getStyle().set("display", "flex");
		 * div.getElement().getStyle().set("align-items", "center");
		 * div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
		 * userName.add(div); userName.getSubMenu().addItem("Sign out", e -> {
		 * authenticatedUser.logout(); });
		 * 
		 * layout.add(userMenu); } else { Anchor loginLink = new Anchor("login",
		 * "Sign in"); layout.add(loginLink); }
		 */

		return layout;
	}

	@Override
	protected void afterNavigation() {
		super.afterNavigation();
		viewTitle.setText(getCurrentPageTitle());
	}

	private String getCurrentPageTitle() {
		PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
		return title == null ? "" : title.value();
	}

	private void createHeader() {
//		H1 logo = new H1("IQRA GROUP IMS");
//		logo.addClassNames("text-l", "m-m");
//		logo.getStyle().set("color", "forestgreen");
		viewTitle = new H2();
		viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
		StreamResource imageResource = new StreamResource("IQRA GROUP",
				() -> getClass().getResourceAsStream("/static/images/logo/IQRA_GROUP.jpg"));
		Image logo = new Image(imageResource, "IQRA GROUP");
		logo.setWidth(10, Unit.PERCENTAGE);
		logo.setHeight(10, Unit.PERCENTAGE);

		Image logo1 = new Image(imageResource, "IQRA GROUP");
		logo1.setWidth(10, Unit.PERCENTAGE);
		logo1.setHeight(10, Unit.PERCENTAGE);
		HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, setAvtar(), logo1);

		header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
		header.setWidth("100%");
		header.addClassNames("py-0", "px-m");
		header.getStyle().set("background-color", "#FFF");
		addToNavbar(header);
	}

	private MenuBar setAvtar() {

		MenuBar menuBar = new MenuBar();
		menuBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY_INLINE);
		menuBar.getStyle().set("margin-left", "auto");

		Avatar avatar = new Avatar("User Image");
		StreamResource imageResource = new StreamResource("User Image",
				() -> getClass().getResourceAsStream("/static/images/default/default_avtar.jpg"));
		avatar.setImageResource(imageResource);

		MenuItem menuItem = menuBar.addItem(avatar);

		SubMenu subMenu = menuItem.getSubMenu();
		subMenu.addItem("Profile");
		subMenu.addItem("Sign out", e -> {
			authenticatedUser.logout();
			UI.getCurrent().navigate(LoginView.class);
		});
		return menuBar;
	}
}
