package com.elastic.support.diagnostics.chain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DiagnosticChainExec {

    private static Logger logger = LogManager.getLogger(DiagnosticChainExec.class);

    public void runDiagnostic(DiagnosticContext context) {

        try {

            String diagType = GlobalContext
                    .getDiagnosticInputs().getDiagType();
            List<String> chain = (List) GlobalContext
                    .getChains().get(diagType);

            Chain diagnostic = new Chain(chain);
            diagnostic.execute(context);

        } catch (Exception e) {
            logger.error("Error encountered running diagnostic. See logs for additional information.  Exiting application.", e);
            throw new RuntimeException("DiagnosticService runtime error", e);
        }

    }

}