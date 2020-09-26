import java.io.IOException;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.RDFLanguages;
import org.apache.jena.util.FileManager;
import org.topbraid.jenax.util.JenaUtil;
import org.topbraid.shacl.validation.ValidationUtil;

public class App {

  public static final String DATA_DIR = "./src/main/resources/sparql/";
  public static final String DATA_FILE = DATA_DIR + "data.ttl";
  public static final String SHAPE_FILE = DATA_DIR + "shape.ttl";
  public static final String QUERY_FILE = DATA_DIR + "query.rq";

  public static void main(String[] args) throws IOException {

//    Model model = JenaUtil.createDefaultModel();
//    FileManager.get().readModel(model, DATA_FILE, RDFLanguages.strLangTurtle);
//
//    Model shapeModel = JenaUtil.createDefaultModel();
//    FileManager.get().readModel(shapeModel, SHAPE_FILE, RDFLanguages.strLangTurtle);
//
//    Resource report = ValidationUtil.validateModel(model, shapeModel, true);
//
//    System.out.println(report.getModel());


  executeQuery();
  }

  public static void executeQuery() {
    Model model = JenaUtil.createDefaultModel();
    FileManager.get().readModel(model, DATA_FILE);

    Query query = QueryFactory.read(QUERY_FILE);
    try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
      ResultSet results = queryExecution.execSelect();
      ResultSetFormatter.out(System.out, results, query);
//      while (results.hasNext()) {
//        QuerySolution querySolution = results.nextSolution();
//        RDFNode x = querySolution.get("");
//        Resource r = querySolution.getResource("c");
//        double l = querySolution.getLiteral("c").getDouble();
//        System.out.println(l);
//      }
    }
  }
}
