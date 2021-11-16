package pa.pro.pa_diplom.config;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import pa.pro.pa_diplom.persistance.jdbc.DbUtils;


import java.util.logging.Logger;

public class Configurator {

    public static final String DAO_TYPE = "daoType";
    public static final String INIT_DB = "initDb";
    public static final String POPULATE_DB = "populateDb";
    private static Logger log = Logger.getLogger(Configurator.class.getName());

    public static void initApp(String [] args){
        long start = System.currentTimeMillis();
        log.info("Initializing APP...");
        log.info("CMD params: {}");




    }

    private static void initDb(Configuration configuration){
        if (configuration.initDb() && configuration.getDaoType() == DaoType.JDBC){
            log.info("Creating new DB");
            DbUtils.initDb();
            log.info("New DB created");

        }
        if (configuration.populateDb()){
            log.info("Population DB with sandbox data");
            if (configuration.getDaoType() == DaoType.JDBC){
                DbUtils.populateJDBC();
            } else if (configuration.getDaoType() == DaoType.IN_MEM){
                DbUtils.populateInMem();
            } else {
                throw new RuntimeException("Could not recognize dao type");            }
        }
        log.info("DB populated");
    }

    private static Configuration createConfig(String [] args) {
        Options options = new Options();
        options.addRequiredOption("dt", DAO_TYPE, true, "choose dao type for this run");
        options.addOption(INIT_DB,false,"flag for initial creation of the DB.");
        options.addOption(POPULATE_DB, false,"flag for populations the DB with fake data");
        try {
            CommandLine cmd = new DefaultParser().parse(options, args);
           Configuration configurator = Configuration.builder()
                   .daoType(cmd.getOptionValue(DAO_TYPE))
                   .initDb(cmd.hasOption(INIT_DB))
                   .populateDb(cmd.hasOption(POPULATE_DB))
                   .build();
          log.info("Configuration building finished");
          return configurator;

        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    public static void main(String[] args) {

        Configuration configuration = createConfig(args);
        initDb(configuration);
    }
}
