package org.gooru.nucleus.gateway.routes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RouteConfiguration implements Iterable<RouteConfigurator> {

    private final Iterator<RouteConfigurator> internalIterator;

    public RouteConfiguration() {
        List<RouteConfigurator> configurators = new ArrayList<>(32);
        // First the global handler to enable to body reading etc
        configurators.add(new RouteGlobalConfigurator());

        // For rest of handlers, Auth should always be first one
        configurators.add(new RouteAuthConfigurator());
        configurators.add(new RouteInternalConfigurator());
        configurators.add(new RouteMetricsConfigurator());
        configurators.add(new RouteResourceConfigurator());
        configurators.add(new RouteQuestionConfigurator());
        configurators.add(new RouteLookupConfigurator());
        configurators.add(new RouteAssessmentConfigurator());
        configurators.add(new RouteAssessmentExternalConfigurator());
        configurators.add(new RouteClassConfigurator());
        configurators.add(new RouteCollectionConfigurator());
        // Order may be important for CUL
        configurators.add(new RouteLessonConfigurator());
        configurators.add(new RouteUnitConfigurator());
        configurators.add(new RouteCourseConfigurator());
        configurators.add(new RouteProfileConfigurator());
        configurators.add(new RouteCopierConfigurator());
        configurators.add(new RouteTaxonomyConfigurator());
        configurators.add(new RouteFailureConfigurator());
        internalIterator = configurators.iterator();
    }

    @Override
    public Iterator<RouteConfigurator> iterator() {
        return new Iterator<RouteConfigurator>() {

            @Override
            public boolean hasNext() {
                return internalIterator.hasNext();
            }

            @Override
            public RouteConfigurator next() {
                return internalIterator.next();
            }

        };
    }

}
