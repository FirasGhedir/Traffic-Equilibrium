% University Ulm
% Algorithm Engineering
% Evaluation
% @author Julian Bestler (julian.bestler@uni-ulm.de)
% @version 1.0


%% paths heuristics
filenameGAMINTB = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\heuristics\50-100\gamintb.txt';
filenameMINTB = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\heuristics\50-100\mintb.txt';

%% paths characteristics
% hint: set your own filepaths here
filenameMinCut = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\50-100\MinCut.txt';
filenameDegeneracy = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\50-100\degeneracy.txt';
filenameDiameter = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\50-100\diameter.txt';
filenameEccentricity = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\50-100\eccentricity.txt';
filenameMaxVertexDegree = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\50-100\maxVertexDegree.txt';
filenameMinVertexDegree = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\50-100\minVertexDegree.txt';
filenameAvgVertexDegree = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\50-100\avgVertexDegree.txt';
filenameRadius = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\50-100\Radius.txt';

%% vectors heuristics
vectorGAMINTB = dlmread(filenameGAMINTB,' ',1,0);
vectorMINTB = dlmread(filenameMINTB,' ',1,0);

%% vectors characteristics
vectorDegeneracy = dlmread(filenameDegeneracy,' ',1,0);
vectorDiameter = dlmread(filenameDiameter,' ',1,0);
vectorEccentricity = dlmread(filenameEccentricity,' ',1,0);
vectorMaxVertexDegree = dlmread(filenameMaxVertexDegree,' ',1,0);
vectorMinVertexDegree = dlmread(filenameMinVertexDegree,' ',1,0);
vectorAvgVertexDegree = dlmread(filenameAvgVertexDegree,' ',1,0);
vectorRadius = dlmread(filenameRadius,' ',1,0);
vectorMinCut = dlmread(filenameMinCut,' ',1,0);

