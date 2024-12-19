package entity.content;

public enum WayPointType {
    ASSUMPTION("assumption"),
    TARGET("target"),
    FUNCTION_ENTER("function_enter"),
    FUNCTION_RETURN("function_return"),
    BRANCHING("branching");

    private final String type;

    WayPointType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
