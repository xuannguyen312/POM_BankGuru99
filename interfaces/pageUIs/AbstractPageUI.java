package pageUIs;

public class AbstractPageUI {
	public static final String DYNAMIC_LINK = "//a[text()='%s']";
	
	public static final String DYNAMIC_TEXTBOX_BUTTON = "//input[@name='%s']";

	public static final String DYNAMIC_RADIO_BUTTON = "//input[@value='%s']";

	public static final String DYNAMIC_TEXT_AREA = "//textarea[@name='%s']";
	
	public static final String DYNAMIC_PAGE_HEADING_NAME = "//p[@class = 'heading3' and text()='%s']";
	
	public static final String DYNAMIC_TABLE_ROW_NAME = "//td[text()='%s']/following-sibling::td";

}
