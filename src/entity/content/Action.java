package entity.content;

public enum Action {
    FOLLOW("follow"),
    AVOID("avoid");
    private final String action;
    Action(String action){
        this.action = action;
    }
    public String getAction(){
        return action;
    }
}
