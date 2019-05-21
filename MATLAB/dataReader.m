% University Ulm
% Algorithm Engineering
% Evaluation
% @author Julian Bestler (julian.bestler@uniulm.de)
% @version 1.0


%% 

% =============================== %
%            GridGraph            %
% =============================== %

%% paths heuristics
filenameGAMINTBGridGraph = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\heuristics\GridGraph\50-100\gamintb.txt';
filenameMINTBGridGraph = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\heuristics\GridGraph\50-100\mintb.txt';

%% paths characteristics
% hint: set your own filepaths here
filenameDegeneracyGridGraph = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\GridGraph\50-100\degeneracy.txt';
filenameDiameterGridGraph = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\GridGraph\50-100\diameter.txt';
filenameEccentricityGridGraph = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\GridGraph\50-100\eccentricity.txt';
filenameMaxVertexDegreeGridGraph = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\GridGraph\50-100\maxVertexDegree.txt';
filenameMinVertexDegreeGridGraph = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\GridGraph\50-100\minVertexDegree.txt';
filenameAvgVertexDegreeGridGraph = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\GridGraph\50-100\avgVertexDegree.txt';
filenameRadiusGridGraph = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\GridGraph\50-100\radius.txt';
filenameMinCutGridGraph = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\GridGraph\50-100\minCut.txt';

%% vectors heuristics
vectorGAMINTBGridGraph = dlmread(filenameGAMINTBGridGraph,' ',1,0);
vectorMINTBGridGraph = dlmread(filenameMINTBGridGraph,' ',1,0);

%% vectors characteristics
vectorDegeneracyGridGraph = dlmread(filenameDegeneracyGridGraph,' ',1,0);
vectorDiameterGridGraph = dlmread(filenameDiameterGridGraph,' ',1,0);
vectorEccentricityGridGraph = dlmread(filenameEccentricityGridGraph,' ',1,0);
vectorMaxVertexDegreeGridGraph = dlmread(filenameMaxVertexDegreeGridGraph,' ',1,0);
vectorMinVertexDegreeGridGraph = dlmread(filenameMinVertexDegreeGridGraph,' ',1,0);
vectorAvgVertexDegreeGridGraph = dlmread(filenameAvgVertexDegreeGridGraph,' ',1,0);
vectorRadiusGridGraph = dlmread(filenameRadiusGridGraph,' ',1,0);
vectorMinCutGridGraph = dlmread(filenameMinCutGridGraph,' ',1,0);

%% 

% =============================== %
%          Poisson graph          %
% =============================== %

%% paths heuristics
% filenameGAMINTBPoisson = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\heuristics\Poisson\50-100\gamintb.txt';
% filenameMINTBPoisson = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\heuristics\Poisson\50-100\mintb.txt';

%% paths characteristics
% hint: set your own filepaths here
filenameDegeneracyPoisson = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\Poisson\50-100\degeneracy.txt';
filenameDiameterPoisson = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\Poisson\50-100\diameter.txt';
filenameEccentricityPoisson = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\Poisson\50-100\eccentricity.txt';
filenameMaxVertexDegreePoisson = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\Poisson\50-100\maxVertexDegree.txt';
filenameMinVertexDegreePoisson = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\Poisson\50-100\minVertexDegree.txt';
filenameAvgVertexDegreePoisson = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\Poisson\50-100\avgVertexDegree.txt';
filenameRadiusPoisson = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\Poisson\50-100\radius.txt';
filenameMinCutPoisson = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\Poisson\50-100\minCut.txt';

%% vectors heuristics
% vectorGAMINTBPoisson = dlmread(filenameGAMINTBPoisson,' ',1,0);
% vectorMINTBPoisson = dlmread(filenameMINTBPoisson,' ',1,0);

%% vectors characteristics
vectorDegeneracyPoisson = dlmread(filenameDegeneracyPoisson,' ',1,0);
vectorDiameterPoisson = dlmread(filenameDiameterPoisson,' ',1,0);
vectorEccentricityPoisson = dlmread(filenameEccentricityPoisson,' ',1,0);
vectorMaxVertexDegreePoisson = dlmread(filenameMaxVertexDegreePoisson,' ',1,0);
vectorMinVertexDegreePoisson = dlmread(filenameMinVertexDegreePoisson,' ',1,0);
vectorAvgVertexDegreePoisson = dlmread(filenameAvgVertexDegreePoisson,' ',1,0);
vectorRadiusPoisson = dlmread(filenameRadiusPoisson,' ',1,0);
vectorMinCutPoisson = dlmread(filenameMinCutPoisson,' ',1,0);