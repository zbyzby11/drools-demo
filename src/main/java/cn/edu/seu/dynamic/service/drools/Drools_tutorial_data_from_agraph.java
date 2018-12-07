package cn.edu.seu.dynamic.service.drools;

import com.franz.agraph.repository.AGRepositoryConnection;
import cn.edu.seu.dynamic.service.data.DataProcess;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.definition.KiePackage;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

/**
 * Drools使用教程
 */
public class Drools_tutorial_data_from_agraph {
    public static void main(String[] args) throws IOException, URISyntaxException {
        AGRepositoryConnection conn = DataProcess.getConnection();

        List<Triple> triples = null;
        try {
            triples = DataProcess.readDataFromDb(conn);
            reasoning1(triples);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (triples == null) {
            return;
        }

        if (null != conn) {
            conn.close();
        }
    }

    public static void reasoning1(List<Triple> triples) {
        long startTime = System.currentTimeMillis();

        InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("user.drl"), ResourceType.DRL);

        Collection<KiePackage> pkgs = kbuilder.getKnowledgePackages();
        kbase.addPackages(pkgs);
        KieSession kSession = kbase.newKieSession();
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error : errors) {
                System.err.println(error);
            }
        }
        for (Triple triple : triples) {
            kSession.insert(triple);
        }

        // 输出推理前的三元组及数量
        System.out.println("Triples num before reasoning: " + kSession.getObjects().toArray().length);
        System.out.println("Triples Before Reasoning:");
        //outputTriples(kSession);


        System.out.println("Execute...");
        // 使规则引擎执行规则
        kSession.fireAllRules();

        // 输出推理后的三元组及数量
        System.out.println("Facts num after reasoning: " + kSession.getObjects().toArray().length);
        System.out.println("Facts After Reasoning:");
        //outputTriples(kSession);

        //Triple newlyAddedTriple = getNewlyAddedTriple(kSession.getObjects().toArray(), triples);

        long endTime = System.currentTimeMillis();
        long runningTime = endTime - startTime;
        // 输出推理时间
        System.out.println("Total time cost: " + runningTime + "ms");

        //return newlyAddedTriple;
    }
    /**
     * 用于输出KieSession中的所有三元组
     *
     * @param kSession
     */
    public static void outputTriples(KieSession kSession) {
        Object[] array2 = kSession.getObjects().toArray();
        for (int i = 0; i < array2.length; i++) {
            System.out.println(1 + i + ": " + array2[i]);
        }
    }

}
