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

%% path runtime
filenameGamintbRuntimeGridGraph = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\Runtime\GridGraph\50-100\gamintbRuntime.txt';
filenameMintbRuntimeGridGraph = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\Runtime\GridGraph\50-100\mintbRuntime.txt';

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

%% vectors runtime
vectorGamintbRuntimeGridGraph = dlmread(filenameGamintbRuntimeGridGraph,' ',1,0);
vectorMintbRuntimeGridGraph = dlmread(filenameMintbRuntimeGridGraph,' ',1,0);


%% 

% =============================== %
%          Poisson graph          %
% =============================== %

%% paths heuristics
filenameGAMINTBPoisson = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\heuristics\Poisson\50-100\gamintb.txt';
filenameMINTBPoisson = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\heuristics\Poisson\50-100\mintb.txt';

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

% %% path runtime
% filenameGamintbRuntimePoisson = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\Runtime\Poisson\50-100\gamintbRuntime.txt';
% filenameMintbRuntimePoisson = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\Runtime\Poisson\50-100\mintbRuntime.txt';

%% vectors heuristics
vectorGAMINTBPoisson = dlmread(filenameGAMINTBPoisson,' ',1,0);
vectorMINTBPoisson = dlmread(filenameMINTBPoisson,' ',1,0);

%% vectors characteristics
vectorDegeneracyPoisson = dlmread(filenameDegeneracyPoisson,' ',1,0);
vectorDiameterPoisson = dlmread(filenameDiameterPoisson,' ',1,0);
vectorEccentricityPoisson = dlmread(filenameEccentricityPoisson,' ',1,0);
vectorMaxVertexDegreePoisson = dlmread(filenameMaxVertexDegreePoisson,' ',1,0);
vectorMinVertexDegreePoisson = dlmread(filenameMinVertexDegreePoisson,' ',1,0);
vectorAvgVertexDegreePoisson = dlmread(filenameAvgVertexDegreePoisson,' ',1,0);
vectorRadiusPoisson = dlmread(filenameRadiusPoisson,' ',1,0);
vectorMinCutPoisson = dlmread(filenameMinCutPoisson,' ',1,0);

% %% vectors runtime
% vectorGamintbRuntimePoisson = dlmread(filenameGamintbRuntimePoisson,' ',1,0);
% vectorMintbRuntimePoisson = dlmread(filenameMintbRuntimePoisson,' ',1,0);


% %% 
% 
% % =============================== %
% %          HeavyTail graph          %
% % =============================== %
% 
% %% paths heuristics
% filenameGAMINTBHeavyTail = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\heuristics\HeavyTail\50-100\gamintb.txt';
% filenameMINTBHeavyTail = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\heuristics\HeavyTail\50-100\mintb.txt';
% 
% %% paths characteristics
% % hint: set your own filepaths here
% filenameDegeneracyHeavyTail = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\HeavyTail\50-100\degeneracy.txt';
% filenameDiameterHeavyTail = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\HeavyTail\50-100\diameter.txt';
% filenameEccentricityHeavyTail = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\HeavyTail\50-100\eccentricity.txt';
% filenameMaxVertexDegreeHeavyTail = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\HeavyTail\50-100\maxVertexDegree.txt';
% filenameMinVertexDegreeHeavyTail = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\HeavyTail\50-100\minVertexDegree.txt';
% filenameAvgVertexDegreeHeavyTail = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\HeavyTail\50-100\avgVertexDegree.txt';
% filenameRadiusHeavyTail = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\HeavyTail\50-100\radius.txt';
% filenameMinCutHeavyTail = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\characteristics\HeavyTail\50-100\minCut.txt';
% 
% %% path runtime
% filenameGamintbRuntimeHeavyTail = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\Runtime\HeavyTail\50-100\gamintbRuntime.txt';
% filenameMintbRuntimeHeavyTail = 'C:\Users\julia\Dropbox\Projekt Algorithm Engineering-Projekt\Traffic-Equilibrium\Masterprojekt\files\Evaluation\Runtime\HeavyTail\50-100\mintbRuntime.txt';
% 
% %% vectors heuristics
% vectorGAMINTBHeavyTail = dlmread(filenameGAMINTBHeavyTail,' ',1,0);
% vectorMINTBHeavyTail = dlmread(filenameMINTBHeavyTail,' ',1,0);
% 
% %% vectors characteristics
% vectorDegeneracyHeavyTail = dlmread(filenameDegeneracyHeavyTail,' ',1,0);
% vectorDiameterHeavyTail = dlmread(filenameDiameterHeavyTail,' ',1,0);
% vectorEccentricityHeavyTail = dlmread(filenameEccentricityHeavyTail,' ',1,0);
% vectorMaxVertexDegreeHeavyTail = dlmread(filenameMaxVertexDegreeHeavyTail,' ',1,0);
% vectorMinVertexDegreeHeavyTail = dlmread(filenameMinVertexDegreeHeavyTail,' ',1,0);
% vectorAvgVertexDegreeHeavyTail = dlmread(filenameAvgVertexDegreeHeavyTail,' ',1,0);
% vectorRadiusHeavyTail = dlmread(filenameRadiusHeavyTail,' ',1,0);
% vectorMinCutHeavyTail = dlmread(filenameMinCutHeavyTail,' ',1,0);
% 
% %% vectors runtime
% vectorGamintbRuntimeHeavyTail = dlmread(filenameGamintbRuntimeHeavyTail,' ',1,0);
% vectorMintbRuntimeHeavyTail = dlmread(filenameMintbRuntimeHeavyTail,' ',1,0);