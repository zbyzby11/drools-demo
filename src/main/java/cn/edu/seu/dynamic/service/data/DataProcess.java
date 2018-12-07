package cn.edu.seu.dynamic.service.data;

import com.franz.agraph.repository.*;
import cn.edu.seu.dynamic.service.drools.Triple;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQueryResult;

import java.util.ArrayList;
import java.util.List;

//import com.franz.agraph.repository.AGRepository;

//import com.franz.agraph.repository.AGRepository;


public class DataProcess {
    static private final String SERVER_URL = "http://10.201.20.139:10035";
    static private final String CATALOG_ID = "demo-catalog";
    static private final String USERNAME = "test";
    static private final String PASSWORD = "19960324";

    //static final String FOAF_NS = "http://xmlns.com/foaf/0.1/";

    public static List<Triple> readDataFromDb(AGRepositoryConnection conn)
            throws Exception {
        List<Triple> triples = new ArrayList<Triple>();

        try {
            String queryString = "SELECT ?s ?p ?o WHERE {?s ?p ?o.}";
            AGTupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
            TupleQueryResult result = tupleQuery.evaluate();

            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value s = bindingSet.getValue("s");
                Value p = bindingSet.getValue("p");
                Value o = bindingSet.getValue("o");

                if (true) {
                    //System.out.format("%s %s %s\n", s, p, o);
                    triples.add(new Triple(s.toString(), p.toString(), o.toString()));
                }
                //break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(triples);
        return triples;
    }

    public static AGRepositoryConnection getConnection() {
        // Tests getting the repository up.
        System.out.println("\nStarting example1().");
        AGServer server = new AGServer(SERVER_URL, USERNAME, PASSWORD);
        System.out.println("Available catalogs: " + server.listCatalogs());

        AGCatalog catalog = server.getCatalog(CATALOG_ID);
        System.out.println("Available repositories in catalog " +
                (catalog.getCatalogName()) + ": " +
                catalog.listRepositories());

        AGRepository app_repo = catalog.openRepository("demo");
        AGRepositoryConnection conn = app_repo.getConnection();

        System.out.println("Got a connection.");
        System.out.println("Repository \"" + (app_repo.getRepositoryID()) +
                "\" is up! It contains " + (conn.size()) +
                " statements."
        );

        return conn;
    }

    public static String string2Unicode(String string) {
        boolean flag = true;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) < 0 || string.charAt(i) > 255) {
                flag = false;
            }
        }
        if (flag) {
            return string;
        }

        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }

    public static boolean isTripleNeedToUpdate(AGRepositoryConnection conn, Triple triple) {
        String[] ls = triple.getPredicate().split("#");
        String queryString = String.format("SELECT ?o WHERE {<%s> <%s> ?o.}", triple.getSubject(), ls[0] + "#" + string2Unicode(ls[1]));
        AGTupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
        TupleQueryResult result = tupleQuery.evaluate();
        boolean truth = result.hasNext();
        return truth;
    }

    public static void insertIntoRepo(AGRepositoryConnection conn, Triple triple) {
        AGValueFactory vf = conn.getRepository().getValueFactory();

        boolean isObjectURI = triple.getObject().startsWith("http");

        conn.add(vf.createURI(triple.getSubject()), vf.createURI(triple.getPredicate()),
                isObjectURI ? vf.createIRI(triple.getObject()) : vf.createLiteral(triple.getObject()));
    }

    public static void updateRepo(AGRepositoryConnection conn, Triple triple) {
        String[] ls = triple.getPredicate().split("#");
        String queryString = String.format("DELETE WHERE {<%s> <%s> ?o.}", triple.getSubject(), ls[0] + "#" + string2Unicode(ls[1]));
        AGTupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
        tupleQuery.evaluate();
        insertIntoRepo(conn, triple);
    }

    public static void main(String[] args) throws Exception {
        AGRepositoryConnection conn = getConnection();
        readDataFromDb(conn);
//        isTripleNeedToUpdate(conn, null);
//        updateRepo(conn,null);
    }

}
