package com.infotop.cms.template;


public class CmsModuleGenerator {
	private static String packName = "com.infotop.cms.template";
	private static String fileName = "jeecms.properties";

	public static void main(String[] args) {
		new ModuleGenerator(packName, fileName).generate();
	}
}
