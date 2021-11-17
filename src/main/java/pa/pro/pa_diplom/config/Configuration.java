package pa.pro.pa_diplom.config;


public class Configuration {
    private final DaoType daoType;
    private final boolean initDb;
    private  final boolean populateDb;

    public Configuration(DaoType daoType, boolean initDb, boolean populateDb) {
        this.daoType = daoType;
        this.initDb = initDb;
        this.populateDb = populateDb;
    }

    public static ConfigBuilder builder(){
        return new ConfigBuilder();
    }

    public DaoType getDaoType() {
        return daoType;
    }

    public boolean initDb(){
        return initDb;
    }
    public boolean populateDb(){
        return populateDb;
    }

    public static class ConfigBuilder{
        private DaoType daoType;
        private Boolean initDb;
        private Boolean populateDb;

        public Configuration build(){
            return new Configuration(daoType, initDb, populateDb);
        }

        public ConfigBuilder daoType (String daoType){
            if (DaoType.IN_MEM.value().equalsIgnoreCase(daoType)){
               this.daoType = DaoType.IN_MEM;
               return this;
            }
            if (DaoType.JDBC.value().equalsIgnoreCase(daoType)){
                this.daoType = DaoType.JDBC;
                return this;
            }
            throw new RuntimeException("SAD! not find a value for dao type");
        }

        public ConfigBuilder initDb (Boolean value){

            this.initDb = value != null? value: false;
            return this;
            }
        public ConfigBuilder populateDb (Boolean value){

            this.populateDb = value != null? value: false;
            return this;
        }

        @Override
        public String toString() {
            return "ConfigBuilder{" +
                    "daoType=" + daoType +
                    ", initDb=" + initDb +
                    ", populateDb=" + populateDb +
                    '}';
        }
    }
    }