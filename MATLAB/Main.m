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

% --- GridGraph ---

% same dimension needed for vectors;
[vectorGAMINTBGridGraph, vectorMINTBGridGraph] = makeVectorsHaveSameDimension(vectorGAMINTBGridGraph, vectorMINTBGridGraph);
% Difference
vectorDifferenceGridGraph = [1,abs(vectorGAMINTBGridGraph-transpose(vectorMINTBGridGraph))];
% Correlation coefficient
RcorrelationcoefficientGridGraph = corrcoef(vectorDegeneracyGridGraph, vectorDiameterGridGraph)

% --- Poisson ---

% % same dimension needed for vectors;
% [vectorGAMINTBPoisson, vectorMINTBPoisson] = makeVectorsHaveSameDimension(vectorGAMINTBPoisson, vectorMINTBPoisson);
% % Difference
% vectorDifferencePoisson = [1,abs(vectorGAMINTBPoisson-transpose(vectorMINTBPoisson))];
% % Correlation coefficient
% RcorrelationcoefficientPoisson = corrcoef(vectorDegeneracyPoisson, vectorDiameterPoisson)


%% --- plot comparison of GAMINTB and MINTB vectors

% --- GridGraph ---
figure;
plot(vectorGAMINTBGridGraph, 'g')
hold on
plot(vectorMINTBGridGraph, 'r')
legend ('GAMINTB','MINTB')
title ('GAMINTB | MINTB with Gridgraph', 'FontSize', 12)
ylabel('Number of tollbooths')
xlabel('Instance')
grid on

% --- Poisson ---
% figure;
% plot(vectorGAMINTBPoisson, 'g')
% hold on
% plot(vectorMINTBPoisson, 'r')
% legend ('GAMINTB','MINTB')
% title ('GAMINTB | MINTB with Poisson', 'FontSize', 12)
% ylabel('Number of tollbooths')
% xlabel('Instance')
% grid on


%% --- plot difference of GAMINTB and MINTB vectors

% --- GridGraph ---
figure;
plot(vectorDifferenceGridGraph, 'b-')
legend ('Difference of tollbooths')
title ('Difference GAMINTB | MINTB with GridGraph', 'FontSize', 12)
ylabel('Tollbooths')
xlabel('Instance')
grid on

% --- Poisson ---
% figure;
% plot(vectorDifferencePoisson, 'b-')
% legend ('Difference of tollbooths')
% title ('Difference GAMINTB | MINTB with Poisson', 'FontSize', 12)
% ylabel('Tollbooths')
% xlabel('Instance')
% grid on


%% --- plot distance of GAMINTB and MINTB vectors

% --- GridGraph ---
figure;
plot(diff(vectorGAMINTBGridGraph), 'g')
hold on
plot(diff(vectorMINTBGridGraph), 'r')
legend ('GAMINTB','MINTB')
title ('Distance GAMINTB | MINTB with GridGraph', 'FontSize', 12)
ylabel('Number of tollbooths')
xlabel('Instance')
grid on

% ---- Poisson ---
% figure;
% plot(diff(vectorGAMINTBPoisson), 'g')
% hold on
% plot(diff(vectorMINTBPoisson), 'r')
% legend ('GAMINTB','MINTB')
% title ('Distance GAMINTB | MINTB with Poisson', 'FontSize', 12)
% ylabel('Number of tollbooths')
% xlabel('Instance')
% grid on


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

featureVectorComparison