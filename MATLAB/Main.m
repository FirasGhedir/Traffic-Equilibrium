% University Ulm
% Algorithm Engineering
% Evaluation
% @author Julian Bestler (julian.bestler@uni-ulm.de)
% @version 1.0

clc;
clear all ;
close all ;

%% --- save console output ---
diary('./output/output.txt')


%% --- read in number of tollbooths and feature vectors ---
dataReader


%% --- sort vectors by number of tollbooths ---
sortVectors


%% --- plot comparison of graph characteristics ---
featureComparison


%% --- performance ---
performanceComparison