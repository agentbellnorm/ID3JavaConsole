package agentbellnorm.story;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Morgan on 2017-01-07.
 *
 * based on https://blog.codecentric.de/en/2012/06/jbehave-configuration-tutorial/
 *
 */

@RunWith(JUnitReportingRunner.class)
public class BaseTestCaseIT extends JUnitStories {

    @Override
    protected List<String> storyPaths(){
        return Arrays.asList("agentbellnorm/story/i_d3_console_happy_flow_i_t.story");
    }

    @Override
    public InjectableStepsFactory stepsFactory(){
        return new InstanceStepsFactory(configuration(), new ID3ConsoleHappyFlowIT());
    }

    @Override
    public Configuration configuration(){
        return new MostUsefulConfiguration();
    }


    public BaseTestCaseIT() {
        super();
        JUnitReportingRunner.recommandedControls(this.configuredEmbedder());
    }
}
