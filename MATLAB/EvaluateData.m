% University Ulm
% Algorithm Engineering
% Evaluation
% @author Julian Bestler (julian.bestler@uni-ulm.de)
% @version 1.0

clc;
clear all ;
close all ;

%% --- read in number of tollbooths ---
% paths heuristics
filenameGAMINTB = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\heuristics\50-100\gamintb.txt';
filenameMINTB = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\heuristics\50-100\mintb.txt';
% paths characteristics
filenameDegeneracy = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\50-100\degeneracy.txt';
filenameDiameter = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\50-100\diameter.txt';
filenameEccentricity = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\50-100\eccentricity.txt';
filenameMaxVertexDegree = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\50-100\maxVertexDegree.txt';
filenameMinVertexDegree = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\50-100\minVertexDegree.txt';
filenameAvgVertexDegree = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\50-100\avgVertexDegree.txt';
filenameRadius = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\50-100\Radius.txt';

% vectors heuristics
vectorGAMINTB = dlmread(filenameGAMINTB,' ',1,0);
vectorMINTB = dlmread(filenameMINTB,' ',1,0);
% vectors characteristics
vectorDegeneracy = dlmread(filenameDegeneracy,' ',1,0);
vectorDiameter = dlmread(filenameDiameter,' ',1,0);
vectorEccentricity = dlmread(filenameEccentricity,' ',1,0);
vectorMaxVertexDegree = dlmread(filenameMaxVertexDegree,' ',1,0);
vectorMinVertexDegree = dlmread(filenameMinVertexDegree,' ',1,0);
vectorAvgVertexDegree = dlmread(filenameAvgVertexDegree,' ',1,0);
vectorRadius = dlmread(filenameRadius,' ',1,0);

%% --- some basic calculations
% same dimension needed for vectors;
[vectorGAMINTB, vectorMINTB] = makeVectorsHaveSameDimension(vectorGAMINTB, vectorMINTB);

vectorDifference = [1,abs(vectorGAMINTB-transpose(vectorMINTB))];


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
% ----------------

% vectorDegeneracy./vectorDiameter 
figure (4);
plot(vectorDegeneracy./vectorDiameter , 'r')
legend ('vectorDegeneracy./vectorDiameter')
title ('vectorDegeneracy./vectorDiameter', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorDegeneracy./vectorEccentricity 
figure (5);
plot(vectorDegeneracy./vectorEccentricity  , 'r')
legend ('vectorDegeneracy./vectorEccentricity')
title ('vectorDegeneracy./vectorEccentricity', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorDegeneracy./vectorMaxVertexDegree
figure (6);
plot(vectorDegeneracy./vectorMaxVertexDegree, 'r')
legend ('vectorDegeneracy./vectorMaxVertexDegree')
title ('vectorDegeneracy./vectorMaxVertexDegree', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorDegeneracy./vectorMinVertexDegree
figure (7);
plot(vectorDegeneracy./vectorMinVertexDegree, 'r')
legend ('vectorDegeneracy./vectorMinVertexDegree')
title ('vectorDegeneracy./vectorMinVertexDegree', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorDegeneracy./vectorAvgVertexDegree
figure (8);
plot(vectorDegeneracy./vectorAvgVertexDegree, 'r')
legend ('vectorDegeneracy./vectorAvgVertexDegree')
title ('vectorDegeneracy./vectorAvgVertexDegree', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorDegeneracy./vectorRadius
figure (9);
plot(vectorDegeneracy./vectorRadius, 'r')
legend ('vectorDegeneracy./vectorRadius')
title ('vectorDegeneracy./vectorRadius', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorDiameter./vectorEccentricity
figure (10);
plot(vectorDiameter./vectorEccentricity, 'r')
legend ('vectorDiameter./vectorEccentricity')
title ('vectorDiameter./vectorEccentricity', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorDiameter./vectorMaxVertexDegree
figure (11);
plot(vectorDiameter./vectorMaxVertexDegree, 'r')
legend ('vectorDiameter./vectorMaxVertexDegree')
title ('vectorDiameter./vectorMaxVertexDegree', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorDiameter./vectorMinVertexDegree
figure (12);
plot(vectorDiameter./vectorMinVertexDegree, 'r')
legend ('vectorDiameter./vectorMinVertexDegree')
title ('vectorDiameter./vectorMinVertexDegree', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorDiameter./vectorAvgVertexDegree
figure (13);
plot(vectorDiameter./vectorAvgVertexDegree, 'r')
legend ('vectorDiameter./vectorAvgVertexDegree')
title ('vectorDiameter./vectorAvgVertexDegree', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorDiameter./vectorRadius
figure (14);
plot(vectorDiameter./vectorRadius, 'r')
legend ('vectorDiameter./vectorRadius')
title ('vectorDiameter./vectorRadius', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorEccentricity./vectorMaxVertexDegree
figure (15);
plot(vectorEccentricity./vectorMaxVertexDegree, 'r')
legend ('vectorEccentricity./vectorMaxVertexDegree')
title ('vectorEccentricity./vectorMaxVertexDegree', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorEccentricity./vectorMinVertexDegree
figure (16);
plot(vectorEccentricity./vectorMinVertexDegree, 'r')
legend ('vectorEccentricity./vectorMinVertexDegree')
title ('vectorEccentricity./vectorMinVertexDegree', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorEccentricity./vectorAvgVertexDegree
figure (17);
plot(vectorEccentricity./vectorAvgVertexDegree, 'r')
legend ('vectorEccentricity./vectorAvgVertexDegree')
title ('vectorEccentricity./vectorAvgVertexDegree', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorEccentricity./vectorRadius
figure (18);
plot(vectorEccentricity./vectorRadius, 'r')
legend ('vectorEccentricity./vectorRadius')
title ('vectorEccentricity./vectorRadius', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorMaxVertexDegree./vectorMinVertexDegree
figure (19);
plot(vectorMaxVertexDegree./vectorMinVertexDegree, 'r')
legend ('vectorMaxVertexDegree./vectorMinVertexDegree')
title ('vectorMaxVertexDegree./vectorMinVertexDegree', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorMaxVertexDegree./vectorAvgVertexDegree
figure (20);
plot(vectorMaxVertexDegree./vectorAvgVertexDegree, 'r')
legend ('vectorMaxVertexDegree./vectorAvgVertexDegree')
title ('vectorMaxVertexDegree./vectorAvgVertexDegree', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorMaxVertexDegree./vectorRadius
figure (21);
plot(vectorMaxVertexDegree./vectorRadius, 'r')
legend ('vectorMaxVertexDegree./vectorRadius')
title ('vectorMaxVertexDegree./vectorRadius', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorMinVertexDegree./vectorAvgVertexDegree
figure (22);
plot(vectorMinVertexDegree./vectorAvgVertexDegree, 'r')
legend ('vectorMinVertexDegree./vectorAvgVertexDegree')
title ('vectorMinVertexDegree./vectorAvgVertexDegree', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorMinVertexDegree./vectorRadius
figure (23);
plot(vectorMinVertexDegree./vectorRadius, 'r')
legend ('vectorMinVertexDegree./vectorRadius')
title ('vectorMinVertexDegree./vectorRadius', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on

% vectorAvgVertexDegree./vectorRadius
figure (24);
plot(vectorAvgVertexDegree./vectorRadius, 'r')
legend ('vectorAvgVertexDegree./vectorRadius')
title ('vectorAvgVertexDegree./vectorRadius', 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on