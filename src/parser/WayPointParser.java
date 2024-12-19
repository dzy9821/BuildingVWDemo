package parser;

import entity.content.Constraint;
import entity.content.Location;
import entity.content.WayPoint;

public class WayPointParser {

    public static WayPoint parseWayPoint(String line, String inputfile){
        Constraint constraint = new Constraint();
        Location location = new Location();


        WayPoint wayPoint = new WayPoint(constraint, location);
        //TODO
        wayPoint.setAction("follow");
        wayPoint.getLocation().setFunction("main");
        wayPoint.getLocation().setFileName(inputfile);

        String[] split = line.split(" ");
        parseWayPointLine(wayPoint, split[1]);
        parseWayPointType(wayPoint, split[2]);
        parseWayConstraint(wayPoint,line);


//        System.out.println();
//        if (wayPoint.getType() != null) System.out.print(wayPoint.getType() + " ");
//        if (wayPoint.getAction() != null) System.out.print(wayPoint.getAction() + " ");
//        if (wayPoint.getConstraint() != null) System.out.print(wayPoint.getConstraint().getValue() + " ");
//        if (wayPoint.getConstraint().getFormat() != null) System.out.print(wayPoint.getConstraint().getFormat() + " ");
//        if (wayPoint.getLocation().getFileName() != null) System.out.print(wayPoint.getLocation().getFileName() + " ");
//        if (wayPoint.getLocation().getLine() != 0) System.out.print(wayPoint.getLocation().getLine() + " ");
//        if (wayPoint.getLocation().getFunction() != null) System.out.print(wayPoint.getLocation().getFunction() + " ");


        return wayPoint;
    }
    public static void parseWayPointType(WayPoint wayPoint, String type){
        wayPoint.setType(type);
    }
    public static void parseWayPointLine(WayPoint wayPoint, String lineNumber){
        wayPoint.getLocation().setLine(Integer.parseInt(lineNumber));
    }
    public static void parseWayConstraint(WayPoint wayPoint, String line){
        String[] split = line.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 3; i < split.length; i++){
            if (i == split.length - 1){
                stringBuilder.append(split[i]);
            }else {
                stringBuilder.append(split[i] + " ");
            }
        }
        if (wayPoint.getType().equals("assumption")){
            wayPoint.getConstraint().setValue("(" + stringBuilder.toString() + ")");
            wayPoint.getConstraint().setFormat("c_expression");
        } else if (wayPoint.getType().equals("branching")) {
            wayPoint.getConstraint().setValue(stringBuilder.toString());
        }else if (wayPoint.getType().equals("target")) {
            wayPoint.setConstraint(null);
        }
    }
}
