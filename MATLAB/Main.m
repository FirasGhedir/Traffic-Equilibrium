% University Ulm
% Algorithm Engineering
% Evaluation
% @author Julian Bestler (julian.bestler@uni-ulm.de)
% @version 1.0

clc;
clear all ;
close all ;

%% --- read in number of tollbooths and feature vectors ---
dataReader

%% --- some basic calculations
% same dimension needed for vectors;
[vectorGAMINTB, vectorMINTB] = makeVectorsHaveSameDimension(vectorGAMINTB, vectorMINTB);
% Difference
vectorDifference = [1,abs(vectorGAMINTB-transpose(vectorMINTB))];
% Correlation coefficient
R_correlationcoefficient = corrcoef(vectorDegeneracy, vectorDiameter)


%% --- plot comparison of GAMINTB and MINTB vectors
figure (1);
plot(vectorGAMINTB, 'g')
hold on
plot(vectorMINTB, 'r')
legend ('GAMINTB','MINTB')
title ('GAMINTB | MINTB', 'FontSize', 12)
ylabel('Number of tollbooths')
xlabel('Instance')
grid on

%% --- plot difference of GAMINTB and MINTB vectors
figure (2);
plot(vectorDifference, 'b-')
legend ('Difference of tollbooths')
title ('Difference GAMINTB | MINTB', 'FontSize', 12)
ylabel('Tollbooths')
xlabel('Instance')
grid on

%% --- plot distance of GAMINTB and MINTB vectors
figure (3);
plot(diff(vectorGAMINTB), 'g')
hold on
plot(diff(vectorMINTB), 'r')
legend ('GAMINTB','MINTB')
title ('Distance GAMINTB | MINTB', 'FontSize', 12)
ylabel('Number of tollbooths')
xlabel('Instance')
grid on

%% --- plot comparison of graph characteristics

% to check:
% ----------------
% vectorDegeneracy 
% vectorDiameter 
% vectorEccentricity 
% vectorMaxVertexDegree 
% vectorMinVertexDegree
% vectorAvgVertexDegree 
% vectorRadius
% vectorMinCut
% ----------------

%featureVectorComparison

% vectorDegeneracy, vectorDiameter 
plotFeatureComparison(vectorDegeneracy, vectorDiameter, 'vectorDegeneracy./vectorDiameter')
r_1 = plotCorrelationCoefficient(vectorDegeneracy, vectorDiameter, 'Correlation vectorDegeneracy,vectorDiameter');

% vectorDegeneracy, vectorEccentricity 
plotFeatureComparison(vectorDegeneracy, vectorEccentricity, 'vectorDegeneracy./vectorEccentricity ')
r_2 = plotCorrelationCoefficient(vectorDegeneracy, vectorEccentricity, 'Correlation vectorDegeneracy,vectorEccentricity');

% vectorDegeneracy, vectorMaxVertexDegree
plotFeatureComparison(vectorDegeneracy, vectorMaxVertexDegree, 'vectorMaxDegeneracy./vectorMaxVertexDegree')
r_3 = plotCorrelationCoefficient(vectorDegeneracy, vectorMaxVertexDegree, 'Correlation vectorDegeneracy,vectorMaxVertexDegree');

% vectorDegeneracy, vectorMinVertexDegree
plotFeatureComparison(vectorDegeneracy, vectorMinVertexDegree, 'vectorDegeneracy./vectorMinVertexDegree')
r_4 = plotCorrelationCoefficient(vectorDegeneracy, vectorMinVertexDegree, 'Correlation vectorDegeneracy,vectorMinVertexDegree');

% vectorDegeneracy, vectorAvgVertexDegree
plotFeatureComparison(vectorDegeneracy, vectorAvgVertexDegree, 'vectorDegeneracy./AvgVertexDegree')
r_5 = plotCorrelationCoefficient(vectorDegeneracy, vectorAvgVertexDegree, 'Correlation vectorDegeneracy,vectorAvgVertexDegree');

% vectorDegeneracy./vectorRadius
plotFeatureComparison(vectorDegeneracy, vectorRadius, 'vectorDegeneracy./vectorRadius')
r_6 = plotCorrelationCoefficient(vectorDegeneracy, vectorRadius, 'Correlation vectorDegeneracy,vectorRadius');

% vectorDiameter./vectorEccentricity
plotFeatureComparison(vectorDiameter, vectorEccentricity, 'vectorDiameter./vectorEccentricity')
r_7 = plotCorrelationCoefficient(vectorDiameter, vectorEccentricity, 'Correlation vectorDiameter,vectorEccentricity');

% vectorDiameter./vectorMinVertexDegree
plotFeatureComparison(vectorDiameter, vectorMinVertexDegree, 'vectorDiameter./vectorMinVertexDegree')
r_8 = plotCorrelationCoefficient(vectorDiameter, vectorMinVertexDegree, 'Correlation vectorDiameter,vectorMinVertexDegree');

% vectorDiameter./vectorAvgVertexDegree
plotFeatureComparison(vectorDiameter, vectorAvgVertexDegree, 'vectorDiameter./vectorAvgVertexDegree')
r_9 = plotCorrelationCoefficient(vectorDiameter, vectorAvgVertexDegree, 'Correlation vectorDiameter,vectorAvgVertexDegree');

% vectorDiameter./vectorRadius
plotFeatureComparison(vectorDiameter, vectorRadius, 'vectorDiameter./vectorRadius')
r_10 = plotCorrelationCoefficient(vectorDiameter, vectorRadius, 'Correlation vectorDiameter,vectorRadius');

% vectorEccentricity./vectorMaxVertexDegree
plotFeatureComparison(vectorEccentricity, vectorMaxVertexDegree, 'vectorEccentricity./vectorMaxVertexDegree')
r_11 = plotCorrelationCoefficient(vectorEccentricity, vectorMaxVertexDegree, 'Correlation vectorEccentricity,vectorRadius');

% vectorEccentricity./vectorMinVertexDegree
plotFeatureComparison(vectorEccentricity, vectorMinVertexDegree, 'vectorEccentricity./vectorMinVertexDegree')
r_12 = plotCorrelationCoefficient(vectorEccentricity, vectorMinVertexDegree, 'Correlation vectorEccentricity,vectorMinVertexDegree');

% vectorEccentricity./vectorAvgVertexDegree
plotFeatureComparison(vectorEccentricity, vectorAvgVertexDegree, 'vectorEccentricity./vectorAvgVertexDegree')
r_13 = plotCorrelationCoefficient(vectorEccentricity, vectorAvgVertexDegree, 'Correlation vectorEccentricity,vectorAvgVertexDegree');

% vectorEccentricity./vectorRadius
plotFeatureComparison(vectorEccentricity, vectorRadius, 'vectorEccentricity./vectorRadius')
r_14 = plotCorrelationCoefficient(vectorEccentricity, vectorRadius, 'Correlation vectorEccentricity,vectorRadius');

% vectorMaxVertexDegree./vectorMinVertexDegree
plotFeatureComparison(vectorMaxVertexDegree, vectorMinVertexDegree, 'vectorMaxVertexDegree./vectorMinVertexDegree')
r_15 = plotCorrelationCoefficient(vectorMaxVertexDegree, vectorMinVertexDegree, 'Correlation vectorMaxVertexDegree,vectorMinVertexDegree');

% vectorMaxVertexDegree./vectorAvgVertexDegree
plotFeatureComparison(vectorMaxVertexDegree, vectorAvgVertexDegree, 'vectorMaxVertexDegree./vectorAvgVertexDegree')
r_16 = plotCorrelationCoefficient(vectorMaxVertexDegree, vectorAvgVertexDegree, 'Correlation vectorMaxVertexDegree,vectorAvgVertexDegree');

% vectorMaxVertexDegree./vectorRadius
plotFeatureComparison(vectorMaxVertexDegree, vectorRadius, 'vectorMaxVertexDegree./vectorRadius')
r_17 = plotCorrelationCoefficient(vectorMaxVertexDegree, vectorRadius, 'Correlation vectorMaxVertexDegree,vectorRadius');

% vectorMinVertexDegree./vectorAvgVertexDegree
plotFeatureComparison(vectorMinVertexDegree, vectorAvgVertexDegree, 'vectorMinVertexDegree./vectorAvgVertexDegree')
r_18 = plotCorrelationCoefficient(vectorMinVertexDegree, vectorAvgVertexDegree, 'Correlation vectorMinVertexDegree,vectorAvgVertexDegree');

% vectorAvgVertexDegree./vectorRadius
plotFeatureComparison(vectorAvgVertexDegree, vectorRadius, 'vectorAvgVertexDegree./vectorRadius')
r_19 = plotCorrelationCoefficient(vectorAvgVertexDegree, vectorRadius, 'Correlation vectorAvgVertexDegree,vectorRadius');

% vectorMinCut./vectorDegeneracy 
plotFeatureComparison(vectorMinCut, vectorDegeneracy, 'vectorMinCut./vectorDegeneracy')
r_20 = plotCorrelationCoefficient(vectorMinCut, vectorDegeneracy, 'Correlation vectorMinCut,vectorDegeneracy');

% vectorMinCut./vectorDiameter 
plotFeatureComparison(vectorMinCut, vectorDiameter, 'vectorMinCut./vectorDiameter')
r_21 = plotCorrelationCoefficient(vectorMinCut, vectorDiameter, 'Correlation vectorMinCut,vectorDiameter');

% vectorMinCut./vectorEccentricity
plotFeatureComparison(vectorMinCut, vectorDiameter, 'vectorMinCut./vectorDiameter')
r_22 = plotCorrelationCoefficient(vectorMinCut, vectorEccentricity, 'Correlation vectorMinCut,vectorEccentricity');

% vectorMinCut./vectorMaxVertexDegree
plotFeatureComparison(vectorMinCut, vectorMaxVertexDegree, 'vectorMinCut./vectorMaxVertexDegree')
r_23 = plotCorrelationCoefficient(vectorMinCut, vectorMaxVertexDegree, 'Correlation vectorMinCut,vectorMaxVertexDegree');

% vectorMinCut./vectorMinVertexDegree
plotFeatureComparison(vectorMinCut, vectorMinVertexDegree, 'vectorMinCut./vectorMinVertexDegree')
r_24 = plotCorrelationCoefficient(vectorMinCut, vectorMinVertexDegree, 'Correlation vectorMinCut,vectorMinVertexDegree');

% vectorMinCut./vectorAvgVertexDegree 
plotFeatureComparison(vectorMinCut, vectorAvgVertexDegree, 'vectorMinCut./vectorAvgVertexDegree')
r_25 = plotCorrelationCoefficient(vectorMinCut, vectorAvgVertexDegree , 'Correlation vectorMinCut,vectorAvgVertexDegree ');

% vectorMinCut./vectorRadius
plotFeatureComparison(vectorMinCut, vectorRadius, 'vectorMinCut./vectorRadius')
r_26 = plotCorrelationCoefficient(vectorMinCut, vectorRadius, 'Correlation vectorMinCut,vectorRadius');
