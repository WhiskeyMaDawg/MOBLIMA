package MobView;

public class printPath {
	  protected void printRoute(String route) {
		    String spaces = String.format("%" + (105 - route.length()) + "s", "");
		    System.out.println(
		        "____________________________________________________________________________________________________________");
		    System.out.println("| " + route + spaces + "|");
		    System.out.println(
		        "____________________________________________________________________________________________________________");
		  }
}
