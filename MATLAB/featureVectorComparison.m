% University Ulm
% Algorithm Engineering
% Evaluation
% @author Julian Bestler (julian.bestler@uni-ulm.de)
% @version 1.0


%% --- GridGraph ---

% vectorDegeneracyGridGraph, vectorDiameterGridGraph 
plotFeatureComparison(vectorDegeneracyGridGraph, vectorDiameterGridGraph, 'vectorDegeneracyGridGraph./vectorDiameterGridGraph')
rGridGraph1 = plotCorrelationCoefficient(vectorDegeneracyGridGraph, vectorDiameterGridGraph, 'Correlation vectorDegeneracyGridGraph,vectorDiameterGridGraph');

% vectorDegeneracyGridGraph, vectorEccentricityGridGraph 
plotFeatureComparison(vectorDegeneracyGridGraph, vectorEccentricityGridGraph, 'vectorDegeneracyGridGraph./vectorEccentricityGridGraph ')
rGridGraph2 = plotCorrelationCoefficient(vectorDegeneracyGridGraph, vectorEccentricityGridGraph, 'Correlation vectorDegeneracyGridGraph,vectorEccentricityGridGraph');

% vectorDegeneracyGridGraph, vectorMaxVertexDegreeGridGraph
plotFeatureComparison(vectorDegeneracyGridGraph, vectorMaxVertexDegreeGridGraph, 'vectorMaxDegeneracy./vectorMaxVertexDegreeGridGraph')
rGridGraph3 = plotCorrelationCoefficient(vectorDegeneracyGridGraph, vectorMaxVertexDegreeGridGraph, 'Correlation vectorDegeneracyGridGraph,vectorMaxVertexDegreeGridGraph');

% vectorDegeneracyGridGraph, vectorMinVertexDegreeGridGraph
plotFeatureComparison(vectorDegeneracyGridGraph, vectorMinVertexDegreeGridGraph, 'vectorDegeneracyGridGraph./vectorMinVertexDegreeGridGraph')
rGridGraph4 = plotCorrelationCoefficient(vectorDegeneracyGridGraph, vectorMinVertexDegreeGridGraph, 'Correlation vectorDegeneracyGridGraph,vectorMinVertexDegreeGridGraph');

% vectorDegeneracyGridGraph, vectorAvgVertexDegreeGridGraph
plotFeatureComparison(vectorDegeneracyGridGraph, vectorAvgVertexDegreeGridGraph, 'vectorDegeneracyGridGraph./AvgVertexDegree')
rGridGraph5 = plotCorrelationCoefficient(vectorDegeneracyGridGraph, vectorAvgVertexDegreeGridGraph, 'Correlation vectorDegeneracyGridGraph,vectorAvgVertexDegreeGridGraph');

% vectorDegeneracyGridGraph./vectorRadiusGridGraph
plotFeatureComparison(vectorDegeneracyGridGraph, vectorRadiusGridGraph, 'vectorDegeneracyGridGraph./vectorRadiusGridGraph')
rGridGraph6 = plotCorrelationCoefficient(vectorDegeneracyGridGraph, vectorRadiusGridGraph, 'Correlation vectorDegeneracyGridGraph,vectorRadiusGridGraph');

% vectorDiameterGridGraph./vectorEccentricityGridGraph
plotFeatureComparison(vectorDiameterGridGraph, vectorEccentricityGridGraph, 'vectorDiameterGridGraph./vectorEccentricityGridGraph')
rGridGraph7 = plotCorrelationCoefficient(vectorDiameterGridGraph, vectorEccentricityGridGraph, 'Correlation vectorDiameterGridGraph,vectorEccentricityGridGraph');

% vectorDiameterGridGraph./vectorMinVertexDegreeGridGraph
plotFeatureComparison(vectorDiameterGridGraph, vectorMinVertexDegreeGridGraph, 'vectorDiameterGridGraph./vectorMinVertexDegreeGridGraph')
rGridGraph8 = plotCorrelationCoefficient(vectorDiameterGridGraph, vectorMinVertexDegreeGridGraph, 'Correlation vectorDiameterGridGraph,vectorMinVertexDegreeGridGraph');

% vectorDiameterGridGraph./vectorAvgVertexDegreeGridGraph
plotFeatureComparison(vectorDiameterGridGraph, vectorAvgVertexDegreeGridGraph, 'vectorDiameterGridGraph./vectorAvgVertexDegreeGridGraph')
rGridGraph9 = plotCorrelationCoefficient(vectorDiameterGridGraph, vectorAvgVertexDegreeGridGraph, 'Correlation vectorDiameterGridGraph,vectorAvgVertexDegreeGridGraph');

% vectorDiameterGridGraph./vectorRadiusGridGraph
plotFeatureComparison(vectorDiameterGridGraph, vectorRadiusGridGraph, 'vectorDiameterGridGraph./vectorRadiusGridGraph')
rGridGraph10 = plotCorrelationCoefficient(vectorDiameterGridGraph, vectorRadiusGridGraph, 'Correlation vectorDiameterGridGraph,vectorRadiusGridGraph');

% vectorEccentricityGridGraph./vectorMaxVertexDegreeGridGraph
plotFeatureComparison(vectorEccentricityGridGraph, vectorMaxVertexDegreeGridGraph, 'vectorEccentricityGridGraph./vectorMaxVertexDegreeGridGraph')
rGridGraph11 = plotCorrelationCoefficient(vectorEccentricityGridGraph, vectorMaxVertexDegreeGridGraph, 'Correlation vectorEccentricityGridGraph,vectorRadiusGridGraph');

% vectorEccentricityGridGraph./vectorMinVertexDegreeGridGraph
plotFeatureComparison(vectorEccentricityGridGraph, vectorMinVertexDegreeGridGraph, 'vectorEccentricityGridGraph./vectorMinVertexDegreeGridGraph')
rGridGraph12 = plotCorrelationCoefficient(vectorEccentricityGridGraph, vectorMinVertexDegreeGridGraph, 'Correlation vectorEccentricityGridGraph,vectorMinVertexDegreeGridGraph');

% vectorEccentricityGridGraph./vectorAvgVertexDegreeGridGraph
plotFeatureComparison(vectorEccentricityGridGraph, vectorAvgVertexDegreeGridGraph, 'vectorEccentricityGridGraph./vectorAvgVertexDegreeGridGraph')
rGridGraph13 = plotCorrelationCoefficient(vectorEccentricityGridGraph, vectorAvgVertexDegreeGridGraph, 'Correlation vectorEccentricityGridGraph,vectorAvgVertexDegreeGridGraph');

% vectorEccentricityGridGraph./vectorRadiusGridGraph
plotFeatureComparison(vectorEccentricityGridGraph, vectorRadiusGridGraph, 'vectorEccentricityGridGraph./vectorRadiusGridGraph')
rGridGraph14 = plotCorrelationCoefficient(vectorEccentricityGridGraph, vectorRadiusGridGraph, 'Correlation vectorEccentricityGridGraph,vectorRadiusGridGraph');

% vectorMaxVertexDegreeGridGraph./vectorMinVertexDegreeGridGraph
plotFeatureComparison(vectorMaxVertexDegreeGridGraph, vectorMinVertexDegreeGridGraph, 'vectorMaxVertexDegreeGridGraph./vectorMinVertexDegreeGridGraph')
rGridGraph15 = plotCorrelationCoefficient(vectorMaxVertexDegreeGridGraph, vectorMinVertexDegreeGridGraph, 'Correlation vectorMaxVertexDegreeGridGraph,vectorMinVertexDegreeGridGraph');

% vectorMaxVertexDegreeGridGraph./vectorAvgVertexDegreeGridGraph
plotFeatureComparison(vectorMaxVertexDegreeGridGraph, vectorAvgVertexDegreeGridGraph, 'vectorMaxVertexDegreeGridGraph./vectorAvgVertexDegreeGridGraph')
rGridGraph16 = plotCorrelationCoefficient(vectorMaxVertexDegreeGridGraph, vectorAvgVertexDegreeGridGraph, 'Correlation vectorMaxVertexDegreeGridGraph,vectorAvgVertexDegreeGridGraph');

% vectorMaxVertexDegreeGridGraph./vectorRadiusGridGraph
plotFeatureComparison(vectorMaxVertexDegreeGridGraph, vectorRadiusGridGraph, 'vectorMaxVertexDegreeGridGraph./vectorRadiusGridGraph')
rGridGraph17 = plotCorrelationCoefficient(vectorMaxVertexDegreeGridGraph, vectorRadiusGridGraph, 'Correlation vectorMaxVertexDegreeGridGraph,vectorRadiusGridGraph');

% vectorMinVertexDegreeGridGraph./vectorAvgVertexDegreeGridGraph
plotFeatureComparison(vectorMinVertexDegreeGridGraph, vectorAvgVertexDegreeGridGraph, 'vectorMinVertexDegreeGridGraph./vectorAvgVertexDegreeGridGraph')
rGridGraph18 = plotCorrelationCoefficient(vectorMinVertexDegreeGridGraph, vectorAvgVertexDegreeGridGraph, 'Correlation vectorMinVertexDegreeGridGraph,vectorAvgVertexDegreeGridGraph');

% vectorAvgVertexDegreeGridGraph./vectorRadiusGridGraph
plotFeatureComparison(vectorAvgVertexDegreeGridGraph, vectorRadiusGridGraph, 'vectorAvgVertexDegreeGridGraph./vectorRadiusGridGraph')
rGridGraph19 = plotCorrelationCoefficient(vectorAvgVertexDegreeGridGraph, vectorRadiusGridGraph, 'Correlation vectorAvgVertexDegreeGridGraph,vectorRadiusGridGraph');

% vectorMinCutGridGraph./vectorDegeneracyGridGraph 
plotFeatureComparison(vectorMinCutGridGraph, vectorDegeneracyGridGraph, 'vectorMinCutGridGraph./vectorDegeneracyGridGraph')
rGridGraph20 = plotCorrelationCoefficient(vectorMinCutGridGraph, vectorDegeneracyGridGraph, 'Correlation vectorMinCutGridGraph,vectorDegeneracyGridGraph');

% vectorMinCutGridGraph./vectorDiameterGridGraph 
plotFeatureComparison(vectorMinCutGridGraph, vectorDiameterGridGraph, 'vectorMinCutGridGraph./vectorDiameterGridGraph')
rGridGraph21 = plotCorrelationCoefficient(vectorMinCutGridGraph, vectorDiameterGridGraph, 'Correlation vectorMinCutGridGraph,vectorDiameterGridGraph');

% vectorMinCutGridGraph./vectorEccentricityGridGraph
plotFeatureComparison(vectorMinCutGridGraph, vectorDiameterGridGraph, 'vectorMinCutGridGraph./vectorDiameterGridGraph')
rGridGraph22 = plotCorrelationCoefficient(vectorMinCutGridGraph, vectorEccentricityGridGraph, 'Correlation vectorMinCutGridGraph,vectorEccentricityGridGraph');

% vectorMinCutGridGraph./vectorMaxVertexDegreeGridGraph
plotFeatureComparison(vectorMinCutGridGraph, vectorMaxVertexDegreeGridGraph, 'vectorMinCutGridGraph./vectorMaxVertexDegreeGridGraph')
rGridGraph23 = plotCorrelationCoefficient(vectorMinCutGridGraph, vectorMaxVertexDegreeGridGraph, 'Correlation vectorMinCutGridGraph,vectorMaxVertexDegreeGridGraph');

% vectorMinCutGridGraph./vectorMinVertexDegreeGridGraph
plotFeatureComparison(vectorMinCutGridGraph, vectorMinVertexDegreeGridGraph, 'vectorMinCutGridGraph./vectorMinVertexDegreeGridGraph')
rGridGraph24 = plotCorrelationCoefficient(vectorMinCutGridGraph, vectorMinVertexDegreeGridGraph, 'Correlation vectorMinCutGridGraph,vectorMinVertexDegreeGridGraph');

% vectorMinCutGridGraph./vectorAvgVertexDegreeGridGraph 
plotFeatureComparison(vectorMinCutGridGraph, vectorAvgVertexDegreeGridGraph, 'vectorMinCutGridGraph./vectorAvgVertexDegreeGridGraph')
rGridGraph25 = plotCorrelationCoefficient(vectorMinCutGridGraph, vectorAvgVertexDegreeGridGraph , 'Correlation vectorMinCutGridGraph,vectorAvgVertexDegreeGridGraph ');

% vectorMinCutGridGraph./vectorRadiusGridGraph
plotFeatureComparison(vectorMinCutGridGraph, vectorRadiusGridGraph, 'vectorMinCutGridGraph./vectorRadiusGridGraph')
rGridGraph26 = plotCorrelationCoefficient(vectorMinCutGridGraph, vectorRadiusGridGraph, 'Correlation vectorMinCutGridGraph,vectorRadiusGridGraph');


%% --- Poisson ---

% vectorDegeneracyPoisson, vectorDiameterPoisson 
plotFeatureComparison(vectorDegeneracyPoisson, vectorDiameterPoisson, 'vectorDegeneracyPoisson./vectorDiameterPoisson')
rPoisson1 = plotCorrelationCoefficient(vectorDegeneracyPoisson, vectorDiameterPoisson, 'Correlation vectorDegeneracyPoisson,vectorDiameterPoisson');

% vectorDegeneracyPoisson, vectorEccentricityPoisson 
plotFeatureComparison(vectorDegeneracyPoisson, vectorEccentricityPoisson, 'vectorDegeneracyPoisson./vectorEccentricityPoisson ')
rPoisson2 = plotCorrelationCoefficient(vectorDegeneracyPoisson, vectorEccentricityPoisson, 'Correlation vectorDegeneracyPoisson,vectorEccentricityPoisson');

% vectorDegeneracyPoisson, vectorMaxVertexDegreePoisson
plotFeatureComparison(vectorDegeneracyPoisson, vectorMaxVertexDegreePoisson, 'vectorMaxDegeneracy./vectorMaxVertexDegreePoisson')
rPoisson3 = plotCorrelationCoefficient(vectorDegeneracyPoisson, vectorMaxVertexDegreePoisson, 'Correlation vectorDegeneracyPoisson,vectorMaxVertexDegreePoisson');

% vectorDegeneracyPoisson, vectorMinVertexDegreePoisson
plotFeatureComparison(vectorDegeneracyPoisson, vectorMinVertexDegreePoisson, 'vectorDegeneracyPoisson./vectorMinVertexDegreePoisson')
rPoisson4 = plotCorrelationCoefficient(vectorDegeneracyPoisson, vectorMinVertexDegreePoisson, 'Correlation vectorDegeneracyPoisson,vectorMinVertexDegreePoisson');

% vectorDegeneracyPoisson, vectorAvgVertexDegreePoisson
plotFeatureComparison(vectorDegeneracyPoisson, vectorAvgVertexDegreePoisson, 'vectorDegeneracyPoisson./AvgVertexDegree')
rPoisson5 = plotCorrelationCoefficient(vectorDegeneracyPoisson, vectorAvgVertexDegreePoisson, 'Correlation vectorDegeneracyPoisson,vectorAvgVertexDegreePoisson');

% vectorDegeneracyPoisson./vectorRadiusPoisson
plotFeatureComparison(vectorDegeneracyPoisson, vectorRadiusPoisson, 'vectorDegeneracyPoisson./vectorRadiusPoisson')
rPoisson6 = plotCorrelationCoefficient(vectorDegeneracyPoisson, vectorRadiusPoisson, 'Correlation vectorDegeneracyPoisson,vectorRadiusPoisson');

% vectorDiameterPoisson./vectorEccentricityPoisson
plotFeatureComparison(vectorDiameterPoisson, vectorEccentricityPoisson, 'vectorDiameterPoisson./vectorEccentricityPoisson')
rPoisson7 = plotCorrelationCoefficient(vectorDiameterPoisson, vectorEccentricityPoisson, 'Correlation vectorDiameterPoisson,vectorEccentricityPoisson');

% vectorDiameterPoisson./vectorMinVertexDegreePoisson
plotFeatureComparison(vectorDiameterPoisson, vectorMinVertexDegreePoisson, 'vectorDiameterPoisson./vectorMinVertexDegreePoisson')
rPoisson8 = plotCorrelationCoefficient(vectorDiameterPoisson, vectorMinVertexDegreePoisson, 'Correlation vectorDiameterPoisson,vectorMinVertexDegreePoisson');

% vectorDiameterPoisson./vectorAvgVertexDegreePoisson
plotFeatureComparison(vectorDiameterPoisson, vectorAvgVertexDegreePoisson, 'vectorDiameterPoisson./vectorAvgVertexDegreePoisson')
rPoisson9 = plotCorrelationCoefficient(vectorDiameterPoisson, vectorAvgVertexDegreePoisson, 'Correlation vectorDiameterPoisson,vectorAvgVertexDegreePoisson');

% vectorDiameterPoisson./vectorRadiusPoisson
plotFeatureComparison(vectorDiameterPoisson, vectorRadiusPoisson, 'vectorDiameterPoisson./vectorRadiusPoisson')
rPoisson10 = plotCorrelationCoefficient(vectorDiameterPoisson, vectorRadiusPoisson, 'Correlation vectorDiameterPoisson,vectorRadiusPoisson');

% vectorEccentricityPoisson./vectorMaxVertexDegreePoisson
plotFeatureComparison(vectorEccentricityPoisson, vectorMaxVertexDegreePoisson, 'vectorEccentricityPoisson./vectorMaxVertexDegreePoisson')
rPoisson11 = plotCorrelationCoefficient(vectorEccentricityPoisson, vectorMaxVertexDegreePoisson, 'Correlation vectorEccentricityPoisson,vectorRadiusPoisson');

% vectorEccentricityPoisson./vectorMinVertexDegreePoisson
plotFeatureComparison(vectorEccentricityPoisson, vectorMinVertexDegreePoisson, 'vectorEccentricityPoisson./vectorMinVertexDegreePoisson')
rPoisson12 = plotCorrelationCoefficient(vectorEccentricityPoisson, vectorMinVertexDegreePoisson, 'Correlation vectorEccentricityPoisson,vectorMinVertexDegreePoisson');

% vectorEccentricityPoisson./vectorAvgVertexDegreePoisson
plotFeatureComparison(vectorEccentricityPoisson, vectorAvgVertexDegreePoisson, 'vectorEccentricityPoisson./vectorAvgVertexDegreePoisson')
rPoisson13 = plotCorrelationCoefficient(vectorEccentricityPoisson, vectorAvgVertexDegreePoisson, 'Correlation vectorEccentricityPoisson,vectorAvgVertexDegreePoisson');

% vectorEccentricityPoisson./vectorRadiusPoisson
plotFeatureComparison(vectorEccentricityPoisson, vectorRadiusPoisson, 'vectorEccentricityPoisson./vectorRadiusPoisson')
rPoisson14 = plotCorrelationCoefficient(vectorEccentricityPoisson, vectorRadiusPoisson, 'Correlation vectorEccentricityPoisson,vectorRadiusPoisson');

% vectorMaxVertexDegreePoisson./vectorMinVertexDegreePoisson
plotFeatureComparison(vectorMaxVertexDegreePoisson, vectorMinVertexDegreePoisson, 'vectorMaxVertexDegreePoisson./vectorMinVertexDegreePoisson')
rPoisson15 = plotCorrelationCoefficient(vectorMaxVertexDegreePoisson, vectorMinVertexDegreePoisson, 'Correlation vectorMaxVertexDegreePoisson,vectorMinVertexDegreePoisson');

% vectorMaxVertexDegreePoisson./vectorAvgVertexDegreePoisson
plotFeatureComparison(vectorMaxVertexDegreePoisson, vectorAvgVertexDegreePoisson, 'vectorMaxVertexDegreePoisson./vectorAvgVertexDegreePoisson')
rPoisson16 = plotCorrelationCoefficient(vectorMaxVertexDegreePoisson, vectorAvgVertexDegreePoisson, 'Correlation vectorMaxVertexDegreePoisson,vectorAvgVertexDegreePoisson');

% vectorMaxVertexDegreePoisson./vectorRadiusPoisson
plotFeatureComparison(vectorMaxVertexDegreePoisson, vectorRadiusPoisson, 'vectorMaxVertexDegreePoisson./vectorRadiusPoisson')
rPoisson17 = plotCorrelationCoefficient(vectorMaxVertexDegreePoisson, vectorRadiusPoisson, 'Correlation vectorMaxVertexDegreePoisson,vectorRadiusPoisson');

% vectorMinVertexDegreePoisson./vectorAvgVertexDegreePoisson
plotFeatureComparison(vectorMinVertexDegreePoisson, vectorAvgVertexDegreePoisson, 'vectorMinVertexDegreePoisson./vectorAvgVertexDegreePoisson')
rPoisson18 = plotCorrelationCoefficient(vectorMinVertexDegreePoisson, vectorAvgVertexDegreePoisson, 'Correlation vectorMinVertexDegreePoisson,vectorAvgVertexDegreePoisson');

% vectorAvgVertexDegreePoisson./vectorRadiusPoisson
plotFeatureComparison(vectorAvgVertexDegreePoisson, vectorRadiusPoisson, 'vectorAvgVertexDegreePoisson./vectorRadiusPoisson')
rPoisson19 = plotCorrelationCoefficient(vectorAvgVertexDegreePoisson, vectorRadiusPoisson, 'Correlation vectorAvgVertexDegreePoisson,vectorRadiusPoisson');

% vectorMinCutPoisson./vectorDegeneracyPoisson 
plotFeatureComparison(vectorMinCutPoisson, vectorDegeneracyPoisson, 'vectorMinCutPoisson./vectorDegeneracyPoisson')
rPoisson20 = plotCorrelationCoefficient(vectorMinCutPoisson, vectorDegeneracyPoisson, 'Correlation vectorMinCutPoisson,vectorDegeneracyPoisson');

% vectorMinCutPoisson./vectorDiameterPoisson 
plotFeatureComparison(vectorMinCutPoisson, vectorDiameterPoisson, 'vectorMinCutPoisson./vectorDiameterPoisson')
rPoisson21 = plotCorrelationCoefficient(vectorMinCutPoisson, vectorDiameterPoisson, 'Correlation vectorMinCutPoisson,vectorDiameterPoisson');

% vectorMinCutPoisson./vectorEccentricityPoisson
plotFeatureComparison(vectorMinCutPoisson, vectorDiameterPoisson, 'vectorMinCutPoisson./vectorDiameterPoisson')
rPoisson22 = plotCorrelationCoefficient(vectorMinCutPoisson, vectorEccentricityPoisson, 'Correlation vectorMinCutPoisson,vectorEccentricityPoisson');

% vectorMinCutPoisson./vectorMaxVertexDegreePoisson
plotFeatureComparison(vectorMinCutPoisson, vectorMaxVertexDegreePoisson, 'vectorMinCutPoisson./vectorMaxVertexDegreePoisson')
rPoisson23 = plotCorrelationCoefficient(vectorMinCutPoisson, vectorMaxVertexDegreePoisson, 'Correlation vectorMinCutPoisson,vectorMaxVertexDegreePoisson');

% vectorMinCutPoisson./vectorMinVertexDegreePoisson
plotFeatureComparison(vectorMinCutPoisson, vectorMinVertexDegreePoisson, 'vectorMinCutPoisson./vectorMinVertexDegreePoisson')
rPoisson24 = plotCorrelationCoefficient(vectorMinCutPoisson, vectorMinVertexDegreePoisson, 'Correlation vectorMinCutPoisson,vectorMinVertexDegreePoisson');

% vectorMinCutPoisson./vectorAvgVertexDegreePoisson 
plotFeatureComparison(vectorMinCutPoisson, vectorAvgVertexDegreePoisson, 'vectorMinCutPoisson./vectorAvgVertexDegreePoisson')
rPoisson25 = plotCorrelationCoefficient(vectorMinCutPoisson, vectorAvgVertexDegreePoisson , 'Correlation vectorMinCutPoisson,vectorAvgVertexDegreePoisson ');

% vectorMinCutPoisson./vectorRadiusPoisson
plotFeatureComparison(vectorMinCutPoisson, vectorRadiusPoisson, 'vectorMinCutPoisson./vectorRadiusPoisson')
rPoisson26 = plotCorrelationCoefficient(vectorMinCutPoisson, vectorRadiusPoisson, 'Correlation vectorMinCutPoisson,vectorRadiusPoisson');


% %% --- HeavyTail ---
% 
% % vectorDegeneracyHeavyTail, vectorDiameterHeavyTail 
% plotFeatureComparison(vectorDegeneracyHeavyTail, vectorDiameterHeavyTail, 'vectorDegeneracyHeavyTail./vectorDiameterHeavyTail')
% rHeavyTail1 = plotCorrelationCoefficient(vectorDegeneracyHeavyTail, vectorDiameterHeavyTail, 'Correlation vectorDegeneracyHeavyTail,vectorDiameterHeavyTail');
% 
% % vectorDegeneracyHeavyTail, vectorEccentricityHeavyTail 
% plotFeatureComparison(vectorDegeneracyHeavyTail, vectorEccentricityHeavyTail, 'vectorDegeneracyHeavyTail./vectorEccentricityHeavyTail ')
% rHeavyTail2 = plotCorrelationCoefficient(vectorDegeneracyHeavyTail, vectorEccentricityHeavyTail, 'Correlation vectorDegeneracyHeavyTail,vectorEccentricityHeavyTail');
% 
% % vectorDegeneracyHeavyTail, vectorMaxVertexDegreeHeavyTail
% plotFeatureComparison(vectorDegeneracyHeavyTail, vectorMaxVertexDegreeHeavyTail, 'vectorMaxDegeneracy./vectorMaxVertexDegreeHeavyTail')
% rHeavyTail3 = plotCorrelationCoefficient(vectorDegeneracyHeavyTail, vectorMaxVertexDegreeHeavyTail, 'Correlation vectorDegeneracyHeavyTail,vectorMaxVertexDegreeHeavyTail');
% 
% % vectorDegeneracyHeavyTail, vectorMinVertexDegreeHeavyTail
% plotFeatureComparison(vectorDegeneracyHeavyTail, vectorMinVertexDegreeHeavyTail, 'vectorDegeneracyHeavyTail./vectorMinVertexDegreeHeavyTail')
% rHeavyTail4 = plotCorrelationCoefficient(vectorDegeneracyHeavyTail, vectorMinVertexDegreeHeavyTail, 'Correlation vectorDegeneracyHeavyTail,vectorMinVertexDegreeHeavyTail');
% 
% % vectorDegeneracyHeavyTail, vectorAvgVertexDegreeHeavyTail
% plotFeatureComparison(vectorDegeneracyHeavyTail, vectorAvgVertexDegreeHeavyTail, 'vectorDegeneracyHeavyTail./AvgVertexDegree')
% rHeavyTail5 = plotCorrelationCoefficient(vectorDegeneracyHeavyTail, vectorAvgVertexDegreeHeavyTail, 'Correlation vectorDegeneracyHeavyTail,vectorAvgVertexDegreeHeavyTail');
% 
% % vectorDegeneracyHeavyTail./vectorRadiusHeavyTail
% plotFeatureComparison(vectorDegeneracyHeavyTail, vectorRadiusHeavyTail, 'vectorDegeneracyHeavyTail./vectorRadiusHeavyTail')
% rHeavyTail6 = plotCorrelationCoefficient(vectorDegeneracyHeavyTail, vectorRadiusHeavyTail, 'Correlation vectorDegeneracyHeavyTail,vectorRadiusHeavyTail');
% 
% % vectorDiameterHeavyTail./vectorEccentricityHeavyTail
% plotFeatureComparison(vectorDiameterHeavyTail, vectorEccentricityHeavyTail, 'vectorDiameterHeavyTail./vectorEccentricityHeavyTail')
% rHeavyTail7 = plotCorrelationCoefficient(vectorDiameterHeavyTail, vectorEccentricityHeavyTail, 'Correlation vectorDiameterHeavyTail,vectorEccentricityHeavyTail');
% 
% % vectorDiameterHeavyTail./vectorMinVertexDegreeHeavyTail
% plotFeatureComparison(vectorDiameterHeavyTail, vectorMinVertexDegreeHeavyTail, 'vectorDiameterHeavyTail./vectorMinVertexDegreeHeavyTail')
% rHeavyTail8 = plotCorrelationCoefficient(vectorDiameterHeavyTail, vectorMinVertexDegreeHeavyTail, 'Correlation vectorDiameterHeavyTail,vectorMinVertexDegreeHeavyTail');
% 
% % vectorDiameterHeavyTail./vectorAvgVertexDegreeHeavyTail
% plotFeatureComparison(vectorDiameterHeavyTail, vectorAvgVertexDegreeHeavyTail, 'vectorDiameterHeavyTail./vectorAvgVertexDegreeHeavyTail')
% rHeavyTail9 = plotCorrelationCoefficient(vectorDiameterHeavyTail, vectorAvgVertexDegreeHeavyTail, 'Correlation vectorDiameterHeavyTail,vectorAvgVertexDegreeHeavyTail');
% 
% % vectorDiameterHeavyTail./vectorRadiusHeavyTail
% plotFeatureComparison(vectorDiameterHeavyTail, vectorRadiusHeavyTail, 'vectorDiameterHeavyTail./vectorRadiusHeavyTail')
% rHeavyTail10 = plotCorrelationCoefficient(vectorDiameterHeavyTail, vectorRadiusHeavyTail, 'Correlation vectorDiameterHeavyTail,vectorRadiusHeavyTail');
% 
% % vectorEccentricityHeavyTail./vectorMaxVertexDegreeHeavyTail
% plotFeatureComparison(vectorEccentricityHeavyTail, vectorMaxVertexDegreeHeavyTail, 'vectorEccentricityHeavyTail./vectorMaxVertexDegreeHeavyTail')
% rHeavyTail11 = plotCorrelationCoefficient(vectorEccentricityHeavyTail, vectorMaxVertexDegreeHeavyTail, 'Correlation vectorEccentricityHeavyTail,vectorRadiusHeavyTail');
% 
% % vectorEccentricityHeavyTail./vectorMinVertexDegreeHeavyTail
% plotFeatureComparison(vectorEccentricityHeavyTail, vectorMinVertexDegreeHeavyTail, 'vectorEccentricityHeavyTail./vectorMinVertexDegreeHeavyTail')
% rHeavyTail12 = plotCorrelationCoefficient(vectorEccentricityHeavyTail, vectorMinVertexDegreeHeavyTail, 'Correlation vectorEccentricityHeavyTail,vectorMinVertexDegreeHeavyTail');
% 
% % vectorEccentricityHeavyTail./vectorAvgVertexDegreeHeavyTail
% plotFeatureComparison(vectorEccentricityHeavyTail, vectorAvgVertexDegreeHeavyTail, 'vectorEccentricityHeavyTail./vectorAvgVertexDegreeHeavyTail')
% rHeavyTail13 = plotCorrelationCoefficient(vectorEccentricityHeavyTail, vectorAvgVertexDegreeHeavyTail, 'Correlation vectorEccentricityHeavyTail,vectorAvgVertexDegreeHeavyTail');
% 
% % vectorEccentricityHeavyTail./vectorRadiusHeavyTail
% plotFeatureComparison(vectorEccentricityHeavyTail, vectorRadiusHeavyTail, 'vectorEccentricityHeavyTail./vectorRadiusHeavyTail')
% rHeavyTail14 = plotCorrelationCoefficient(vectorEccentricityHeavyTail, vectorRadiusHeavyTail, 'Correlation vectorEccentricityHeavyTail,vectorRadiusHeavyTail');
% 
% % vectorMaxVertexDegreeHeavyTail./vectorMinVertexDegreeHeavyTail
% plotFeatureComparison(vectorMaxVertexDegreeHeavyTail, vectorMinVertexDegreeHeavyTail, 'vectorMaxVertexDegreeHeavyTail./vectorMinVertexDegreeHeavyTail')
% rHeavyTail15 = plotCorrelationCoefficient(vectorMaxVertexDegreeHeavyTail, vectorMinVertexDegreeHeavyTail, 'Correlation vectorMaxVertexDegreeHeavyTail,vectorMinVertexDegreeHeavyTail');
% 
% % vectorMaxVertexDegreeHeavyTail./vectorAvgVertexDegreeHeavyTail
% plotFeatureComparison(vectorMaxVertexDegreeHeavyTail, vectorAvgVertexDegreeHeavyTail, 'vectorMaxVertexDegreeHeavyTail./vectorAvgVertexDegreeHeavyTail')
% rHeavyTail16 = plotCorrelationCoefficient(vectorMaxVertexDegreeHeavyTail, vectorAvgVertexDegreeHeavyTail, 'Correlation vectorMaxVertexDegreeHeavyTail,vectorAvgVertexDegreeHeavyTail');
% 
% % vectorMaxVertexDegreeHeavyTail./vectorRadiusHeavyTail
% plotFeatureComparison(vectorMaxVertexDegreeHeavyTail, vectorRadiusHeavyTail, 'vectorMaxVertexDegreeHeavyTail./vectorRadiusHeavyTail')
% rHeavyTail17 = plotCorrelationCoefficient(vectorMaxVertexDegreeHeavyTail, vectorRadiusHeavyTail, 'Correlation vectorMaxVertexDegreeHeavyTail,vectorRadiusHeavyTail');
% 
% % vectorMinVertexDegreeHeavyTail./vectorAvgVertexDegreeHeavyTail
% plotFeatureComparison(vectorMinVertexDegreeHeavyTail, vectorAvgVertexDegreeHeavyTail, 'vectorMinVertexDegreeHeavyTail./vectorAvgVertexDegreeHeavyTail')
% rHeavyTail18 = plotCorrelationCoefficient(vectorMinVertexDegreeHeavyTail, vectorAvgVertexDegreeHeavyTail, 'Correlation vectorMinVertexDegreeHeavyTail,vectorAvgVertexDegreeHeavyTail');
% 
% % vectorAvgVertexDegreeHeavyTail./vectorRadiusHeavyTail
% plotFeatureComparison(vectorAvgVertexDegreeHeavyTail, vectorRadiusHeavyTail, 'vectorAvgVertexDegreeHeavyTail./vectorRadiusHeavyTail')
% rHeavyTail19 = plotCorrelationCoefficient(vectorAvgVertexDegreeHeavyTail, vectorRadiusHeavyTail, 'Correlation vectorAvgVertexDegreeHeavyTail,vectorRadiusHeavyTail');
% 
% % vectorMinCutHeavyTail./vectorDegeneracyHeavyTail 
% plotFeatureComparison(vectorMinCutHeavyTail, vectorDegeneracyHeavyTail, 'vectorMinCutHeavyTail./vectorDegeneracyHeavyTail')
% rHeavyTail20 = plotCorrelationCoefficient(vectorMinCutHeavyTail, vectorDegeneracyHeavyTail, 'Correlation vectorMinCutHeavyTail,vectorDegeneracyHeavyTail');
% 
% % vectorMinCutHeavyTail./vectorDiameterHeavyTail 
% plotFeatureComparison(vectorMinCutHeavyTail, vectorDiameterHeavyTail, 'vectorMinCutHeavyTail./vectorDiameterHeavyTail')
% rHeavyTail21 = plotCorrelationCoefficient(vectorMinCutHeavyTail, vectorDiameterHeavyTail, 'Correlation vectorMinCutHeavyTail,vectorDiameterHeavyTail');
% 
% % vectorMinCutHeavyTail./vectorEccentricityHeavyTail
% plotFeatureComparison(vectorMinCutHeavyTail, vectorDiameterHeavyTail, 'vectorMinCutHeavyTail./vectorDiameterHeavyTail')
% rHeavyTail22 = plotCorrelationCoefficient(vectorMinCutHeavyTail, vectorEccentricityHeavyTail, 'Correlation vectorMinCutHeavyTail,vectorEccentricityHeavyTail');
% 
% % vectorMinCutHeavyTail./vectorMaxVertexDegreeHeavyTail
% plotFeatureComparison(vectorMinCutHeavyTail, vectorMaxVertexDegreeHeavyTail, 'vectorMinCutHeavyTail./vectorMaxVertexDegreeHeavyTail')
% rHeavyTail23 = plotCorrelationCoefficient(vectorMinCutHeavyTail, vectorMaxVertexDegreeHeavyTail, 'Correlation vectorMinCutHeavyTail,vectorMaxVertexDegreeHeavyTail');
% 
% % vectorMinCutHeavyTail./vectorMinVertexDegreeHeavyTail
% plotFeatureComparison(vectorMinCutHeavyTail, vectorMinVertexDegreeHeavyTail, 'vectorMinCutHeavyTail./vectorMinVertexDegreeHeavyTail')
% rHeavyTail24 = plotCorrelationCoefficient(vectorMinCutHeavyTail, vectorMinVertexDegreeHeavyTail, 'Correlation vectorMinCutHeavyTail,vectorMinVertexDegreeHeavyTail');
% 
% % vectorMinCutHeavyTail./vectorAvgVertexDegreeHeavyTail 
% plotFeatureComparison(vectorMinCutHeavyTail, vectorAvgVertexDegreeHeavyTail, 'vectorMinCutHeavyTail./vectorAvgVertexDegreeHeavyTail')
% rHeavyTail25 = plotCorrelationCoefficient(vectorMinCutHeavyTail, vectorAvgVertexDegreeHeavyTail , 'Correlation vectorMinCutHeavyTail,vectorAvgVertexDegreeHeavyTail ');
% 
% % vectorMinCutHeavyTail./vectorRadiusHeavyTail
% plotFeatureComparison(vectorMinCutHeavyTail, vectorRadiusHeavyTail, 'vectorMinCutHeavyTail./vectorRadiusHeavyTail')
% rHeavyTail26 = plotCorrelationCoefficient(vectorMinCutHeavyTail, vectorRadiusHeavyTail, 'Correlation vectorMinCutHeavyTail,vectorRadiusHeavyTail');