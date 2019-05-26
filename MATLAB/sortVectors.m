% University Ulm
% Algorithm Engineering
% Evaluation
% @author Julian Bestler (julian.bestler@uni-ulm.de)
% @version 1.0


%% 

% =============================== %
%            GridGraph            %
% =============================== %

% ---- GAMINTB ----
[vectorGamintbGridGraphSorted,order] = sort(vectorGAMINTBGridGraph);

% vectors to sort by  number of toolbooths:
% -----------------------------------------
%    ->  vectorDegeneracyGridGraph
%    ->  vectorDiameterGridGraph
%    ->  vectorEccentricityGridGraph
%    ->  vectorMaxVertexDegreeGridGraph
%    ->  vectorMinVertexDegreeGridGraph
%    ->  vectorAvgVertexDegreeGridGraph
%    ->  vectorRadiusGridGraph
%    ->  vectorMinCutGridGraph
%    ->  vectorGamintbRuntime

vectorDegeneracyGridGraphSortedByGamintb = vectorDegeneracyGridGraph(order);
vectorDiameterGridGraphSortedByGamintb = vectorDiameterGridGraph(order);
vectorEccentricityGridGraphSortedByGamintb = vectorEccentricityGridGraph(order);
vectorMaxVertexDegreeGridGraphSortedByGamintb = vectorMaxVertexDegreeGridGraph(order);
vectorMinVertexDegreeGridGraphSortedByGamintb = vectorMinVertexDegreeGridGraph(order);
vectorAvgVertexDegreeGridGraphSortedByGamintb = vectorAvgVertexDegreeGridGraph(order);
vectorRadiusGridGraphSortedByGamintb = vectorRadiusGridGraph(order);
vectorMinCutGridGraphSortedByGamintb = vectorMinCutGridGraph(order);
vectorGamintbRuntimeGridGraphSortedByGamintb = vectorGamintbRuntimeGridGraph(order);


% ---- MINTB ----
[vectorMintbGridGraphSorted,order] = sort(vectorMINTBGridGraph);

% vectors to sort by  number of toolbooths:
% -----------------------------------------
%    ->  vectorDegeneracyGridGraph
%    ->  vectorDiameterGridGraph
%    ->  vectorEccentricityGridGraph
%    ->  vectorMaxVertexDegreeGridGraph
%    ->  vectorMinVertexDegreeGridGraph
%    ->  vectorAvgVertexDegreeGridGraph
%    ->  vectorRadiusGridGraph
%    ->  vectorMinCutGridGraph
%    ->  vectorMintbRuntime

vectorDegeneracyGridGraphSortedByMintb = vectorDegeneracyGridGraph(order);
vectorDiameterGridGraphSortedByMintb = vectorDiameterGridGraph(order);
vectorEccentricityGridGraphSortedByMintb = vectorEccentricityGridGraph(order);
vectorMaxVertexDegreeGridGraphSortedByMintb = vectorMaxVertexDegreeGridGraph(order);
vectorMinVertexDegreeGridGraphSortedByMintb = vectorMinVertexDegreeGridGraph(order);
vectorAvgVertexDegreeGridGraphSortedByMintb = vectorAvgVertexDegreeGridGraph(order);
vectorRadiusGridGraphSortedByMintb = vectorRadiusGridGraph(order);
vectorMinCutGridGraphSortedByMintb = vectorMinCutGridGraph(order);
vectorMintbRuntimeGridGraphSortedByMintb = vectorMintbRuntimeGridGraph(order);


%% 

% =============================== %
%             Poisson             %
% =============================== %

% ---- GAMINTB ----
[vectorGamintbPoissonSorted,order] = sort(vectorGAMINTBPoisson);

% vectors to sort by  number of toolbooths:
% -----------------------------------------
%    ->  vectorDegeneracyPoisson
%    ->  vectorDiameterPoisson
%    ->  vectorEccentricityPoisson
%    ->  vectorMaxVertexDegreePoisson
%    ->  vectorMinVertexDegreePoisson
%    ->  vectorAvgVertexDegreePoisson
%    ->  vectorRadiusPoisson
%    ->  vectorMinCutPoisson
%    ->  vectorGamintbRuntime

vectorDegeneracyPoissonSortedByGamintb = vectorDegeneracyPoisson(order);
vectorDiameterPoissonSortedByGamintb = vectorDiameterPoisson(order);
vectorEccentricityPoissonSortedByGamintb = vectorEccentricityPoisson(order);
vectorMaxVertexDegreePoissonSortedByGamintb = vectorMaxVertexDegreePoisson(order);
vectorMinVertexDegreePoissonSortedByGamintb = vectorMinVertexDegreePoisson(order);
vectorAvgVertexDegreePoissonSortedByGamintb = vectorAvgVertexDegreePoisson(order);
vectorRadiusPoissonSortedByGamintb = vectorRadiusPoisson(order);
vectorMinCutPoissonSortedByGamintb = vectorMinCutPoisson(order);
vectorGamintbRuntimePoissonSortedByGamintb = vectorGamintbRuntimePoisson(order);


% ---- MINTB ----
[vectorMintbPoissonSorted,order] = sort(vectorMINTBPoisson);

% vectors to sort by  number of toolbooths:
% -----------------------------------------
%    ->  vectorDegeneracyPoisson
%    ->  vectorDiameterPoisson
%    ->  vectorEccentricityPoisson
%    ->  vectorMaxVertexDegreePoisson
%    ->  vectorMinVertexDegreePoisson
%    ->  vectorAvgVertexDegreePoisson
%    ->  vectorRadiusPoisson
%    ->  vectorMinCutPoisson
%    ->  vectorMintbRuntime

vectorDegeneracyPoissonSortedByMintb = vectorDegeneracyPoisson(order);
vectorDiameterPoissonSortedByMintb = vectorDiameterPoisson(order);
vectorEccentricityPoissonSortedByMintb = vectorEccentricityPoisson(order);
vectorMaxVertexDegreePoissonSortedByMintb = vectorMaxVertexDegreePoisson(order);
vectorMinVertexDegreePoissonSortedByMintb = vectorMinVertexDegreePoisson(order);
vectorAvgVertexDegreePoissonSortedByMintb = vectorAvgVertexDegreePoisson(order);
vectorRadiusPoissonSortedByMintb = vectorRadiusPoisson(order);
vectorMinCutPoissonSortedByMintb = vectorMinCutPoisson(order);
vectorMintbRuntimePoissonSortedByMintb = vectorMintbRuntimePoisson(order);


%% 

% =============================== %
%            HeavyTail            %
% =============================== %

% ---- GAMINTB ----
[vectorGamintbHeavyTailSorted,order] = sort(vectorGAMINTBHeavyTail);

% vectors to sort by  number of toolbooths:
% -----------------------------------------
%    ->  vectorDegeneracyHeavyTail
%    ->  vectorDiameterHeavyTail
%    ->  vectorEccentricityHeavyTail
%    ->  vectorMaxVertexDegreeHeavyTail
%    ->  vectorMinVertexDegreeHeavyTail
%    ->  vectorAvgVertexDegreeHeavyTail
%    ->  vectorRadiusHeavyTail
%    ->  vectorMinCutHeavyTail
%    ->  vectorGamintbRuntime

vectorDegeneracyHeavyTailSortedByGamintb = vectorDegeneracyHeavyTail(order);
vectorDiameterHeavyTailSortedByGamintb = vectorDiameterHeavyTail(order);
vectorEccentricityHeavyTailSortedByGamintb = vectorEccentricityHeavyTail(order);
vectorMaxVertexDegreeHeavyTailSortedByGamintb = vectorMaxVertexDegreeHeavyTail(order);
vectorMinVertexDegreeHeavyTailSortedByGamintb = vectorMinVertexDegreeHeavyTail(order);
vectorAvgVertexDegreeHeavyTailSortedByGamintb = vectorAvgVertexDegreeHeavyTail(order);
vectorRadiusHeavyTailSortedByGamintb = vectorRadiusHeavyTail(order);
vectorMinCutHeavyTailSortedByGamintb = vectorMinCutHeavyTail(order);
vectorGamintbRuntimeHeavyTailSortedByGamintb = vectorGamintbRuntimeHeavyTail(order);


% ---- MINTB ----
[vectorMintbHeavyTailSorted,order] = sort(vectorMINTBHeavyTail);

% vectors to sort by  number of toolbooths:
% -----------------------------------------
%    ->  vectorDegeneracyHeavyTail
%    ->  vectorDiameterHeavyTail
%    ->  vectorEccentricityHeavyTail
%    ->  vectorMaxVertexDegreeHeavyTail
%    ->  vectorMinVertexDegreeHeavyTail
%    ->  vectorAvgVertexDegreeHeavyTail
%    ->  vectorRadiusHeavyTail
%    ->  vectorMinCutHeavyTail
%    ->  vectorMintbRuntime

vectorDegeneracyHeavyTailSortedByMintb = vectorDegeneracyHeavyTail(order);
vectorDiameterHeavyTailSortedByMintb = vectorDiameterHeavyTail(order);
vectorEccentricityHeavyTailSortedByMintb = vectorEccentricityHeavyTail(order);
vectorMaxVertexDegreeHeavyTailSortedByMintb = vectorMaxVertexDegreeHeavyTail(order);
vectorMinVertexDegreeHeavyTailSortedByMintb = vectorMinVertexDegreeHeavyTail(order);
vectorAvgVertexDegreeHeavyTailSortedByMintb = vectorAvgVertexDegreeHeavyTail(order);
vectorRadiusHeavyTailSortedByMintb = vectorRadiusHeavyTail(order);
vectorMinCutHeavyTailSortedByMintb = vectorMinCutHeavyTail(order);
vectorMintbRuntimeHeavyTailSortedByMintb = vectorMintbRuntimeHeavyTail(order);