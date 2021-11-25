package pa.pro.pa_diplom.config;

public enum DaoType {

    IN_MEM("inMem"), JDBC("jdbc");

    private final String value;

    DaoType (String value){
        this.value = value;
    }

    public String value(){
        return value;
    }
}
