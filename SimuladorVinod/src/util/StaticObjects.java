package util;

import main.UnidadeControle;

public class StaticObjects { //TODO transformar em uma factory de singletons
	private static UnidadeControle uc;
	
	public static UnidadeControle getUc() {
		return uc;
	}
	public static void setUc(UnidadeControle uc) {
		StaticObjects.uc = uc;
	}
	
}
