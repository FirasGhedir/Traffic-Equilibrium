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
filenameGAMINTBGridGraph = '.\Evaluation\heuristics\GridGraph\50-100\gamintb.txt';
filenameMINTBGridGraph = '.\Evaluation\heuristics\GridGraph\50-100\mintb.txt';

%% paths characteristics
% hint: set your own filepaths here
filenameDegeneracyGridGraph = '.\Evaluation\characteristics\GridGraph\50-100\degeneracy.txt';
filenameDiameterGridGraph = '.\Evaluation\characteristics\GridGraph\50-100\diameter.txt';
filenameEccentricityGridGraph = '.\Evaluation\characteristics\GridGraph\50-100\eccentricity.txt';
filenameMaxVertexDegreeGridGraph = '.\Evaluation\characteristics\GridGraph\50-100\maxVertexDegree.txt';
filenameMinVertexDegreeGridGraph = '.\Evaluation\characteristics\GridGraph\50-100\minVertexDegree.txt';
filenameAvgVertexDegreeGridGraph = '.\Evaluation\characteristics\GridGraph\50-100\avgVertexDegree.txt';
filenameRadiusGridGraph = '.\Evaluation\characteristics\GridGraph\50-100\radius.txt';
filenameMinCutGridGraph = '.\Evaluation\characteristics\GridGraph\50-100\minCut.txt';

%% path runtime
filenameGamintbRuntimeGridGraph = '.\Evaluation\Runtime\GridGraph\50-100\gamintbRuntime.txt';
filenameMintbRuntimeGridGraph = '.\Evaluation\Runtime\GridGraph\50-100\mintbRuntime.txt';

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
filenameGAMINTBPoisson = '.\Evaluation\heuristics\Poisson\50-100\gamintb.txt';
filenameMINTBPoisson = '.\Evaluation\heuristics\Poisson\50-100\mintb.txt';

%% paths characteristics
% hint: set your own filepaths here
filenameDegeneracyPoisson = '.\Evaluation\characteristics\Poisson\50-100\degeneracy.txt';
filenameDiameterPoisson = '.\Evaluation\characteristics\Poisson\50-100\diameter.txt';
filenameEccentricityPoisson = '.\Evaluation\characteristics\Poisson\50-100\eccentricity.txt';
filenameMaxVertexDegreePoisson = '.\Evaluation\characteristics\Poisson\50-100\maxVertexDegree.txt';
filenameMinVertexDegreePoisson = '.\Evaluation\characteristics\Poisson\50-100\minVertexDegree.txt';
filenameAvgVertexDegreePoisson = '.\Evaluation\characteristics\Poisson\50-100\avgVertexDegree.txt';
filenameRadiusPoisson = '.\Evaluation\characteristics\Poisson\50-100\radius.txt';
filenameMinCutPoisson = '.\Evaluation\characteristics\Poisson\50-100\minCut.txt';

%% path runtime
filenameGamintbRuntimePoisson = '.\Evaluation\Runtime\Poisson\50-100\gamintbRuntime.txt';
filenameMintbRuntimePoisson = '.\Evaluation\Runtime\Poisson\50-100\mintbRuntime.txt';

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

%% vectors runtime
vectorGamintbRuntimePoisson = dlmread(filenameGamintbRuntimePoisson,' ',1,0);
vectorMintbRuntimePoisson = dlmread(filenameMintbRuntimePoisson,' ',1,0);

%% ---- edited data ----

%% paths heuristics
filenameGAMINTBPoissonEdited = '.\Evaluation\heuristics\Poisson\uncomplete\50-100\gamintb.txt';
filenameMINTBPoissonEdited = '.\Evaluation\heuristics\Poisson\uncomplete\50-100\mintb.txt';

%% paths characteristics
% hint: set your own filepaths here
filenameDegeneracyPoissonEdited = '.\Evaluation\characteristics\Poisson\uncomplete\50-100\degeneracy.txt';
filenameDiameterPoissonEdited = '.\Evaluation\characteristics\Poisson\uncomplete\50-100\diameter.txt';
filenameEccentricityPoissonEdited = '.\Evaluation\characteristics\Poisson\uncomplete\50-100\eccentricity.txt';
filenameMaxVertexDegreePoissonEdited = '.\Evaluation\characteristics\Poisson\uncomplete\50-100\maxVertexDegree.txt';
filenameMinVertexDegreePoissonEdited = '.\Evaluation\characteristics\Poisson\uncomplete\50-100\minVertexDegree.txt';
filenameAvgVertexDegreePoissonEdited = '.\Evaluation\characteristics\Poisson\uncomplete\50-100\avgVertexDegree.txt';
filenameRadiusPoissonEdited = '.\Evaluation\characteristics\Poisson\uncomplete\50-100\radius.txt';
filenameMinCutPoissonEdited = '.\Evaluation\characteristics\Poisson\uncomplete\50-100\minCut.txt';

%% path runtime
filenameGamintbRuntimePoissonEdited = '.\Evaluation\runtime\Poisson\uncomplete\50-100\gamintbRuntime.txt';
filenameMintbRuntimePoissonEdited = '.\Evaluation\runtime\Poisson\uncomplete\50-100\mintbRuntime.txt';

%% vectors heuristics
vectorGAMINTBPoissonEdited = dlmread(filenameGAMINTBPoissonEdited,' ',1,0);
vectorMINTBPoissonEdited = dlmread(filenameMINTBPoissonEdited,' ',1,0);

%% vectors characteristics
vectorDegeneracyPoissonEdited = dlmread(filenameDegeneracyPoissonEdited,' ',1,0);
vectorDiameterPoissonEdited = dlmread(filenameDiameterPoissonEdited,' ',1,0);
vectorEccentricityPoissonEdited = dlmread(filenameEccentricityPoissonEdited,' ',1,0);
vectorMaxVertexDegreePoissonEdited = dlmread(filenameMaxVertexDegreePoissonEdited,' ',1,0);
vectorMinVertexDegreePoissonEdited = dlmread(filenameMinVertexDegreePoissonEdited,' ',1,0);
vectorAvgVertexDegreePoissonEdited = dlmread(filenameAvgVertexDegreePoissonEdited,' ',1,0);
vectorRadiusPoissonEdited = dlmread(filenameRadiusPoissonEdited,' ',1,0);
vectorMinCutPoissonEdited = dlmread(filenameMinCutPoissonEdited,' ',1,0);

%% vectors runtime
vectorGamintbRuntimePoissonEdited = dlmread(filenameGamintbRuntimePoissonEdited,' ',1,0);
vectorMintbRuntimePoissonEdited = dlmread(filenameMintbRuntimePoissonEdited,' ',1,0);


%% 

% =============================== %
%          HeavyTail graph          %
% =============================== %

%% paths heuristics
filenameGAMINTBHeavyTail = '.\Evaluation\heuristics\HeavyTail\50-100\gamintb.txt';
filenameMINTBHeavyTail = '.\Evaluation\heuristics\HeavyTail\50-100\mintb.txt';

%% paths characteristics
% hint: set your own filepaths here
filenameDegeneracyHeavyTail = '.\Evaluation\characteristics\HeavyTail\50-100\degeneracy.txt';
filenameDiameterHeavyTail = '.\Evaluation\characteristics\HeavyTail\50-100\diameter.txt';
filenameEccentricityHeavyTail = '.\Evaluation\characteristics\HeavyTail\50-100\eccentricity.txt';
filenameMaxVertexDegreeHeavyTail = '.\Evaluation\characteristics\HeavyTail\50-100\maxVertexDegree.txt';
filenameMinVertexDegreeHeavyTail = '.\Evaluation\characteristics\HeavyTail\50-100\minVertexDegree.txt';
filenameAvgVertexDegreeHeavyTail = '.\Evaluation\characteristics\HeavyTail\50-100\avgVertexDegree.txt';
filenameRadiusHeavyTail = '.\Evaluation\characteristics\HeavyTail\50-100\radius.txt';
filenameMinCutHeavyTail = '.\Evaluation\characteristics\HeavyTail\50-100\minCut.txt';

%% path runtime
filenameGamintbRuntimeHeavyTail = '.\Evaluation\Runtime\HeavyTail\50-100\gamintbRuntime.txt';
filenameMintbRuntimeHeavyTail = '.\Evaluation\Runtime\HeavyTail\50-100\mintbRuntime.txt';

%% vectors heuristics
vectorGAMINTBHeavyTail = dlmread(filenameGAMINTBHeavyTail,' ',1,0);
vectorMINTBHeavyTail = dlmread(filenameMINTBHeavyTail,' ',1,0);

%% vectors characteristics
vectorDegeneracyHeavyTail = dlmread(filenameDegeneracyHeavyTail,' ',1,0);
vectorDiameterHeavyTail = dlmread(filenameDiameterHeavyTail,' ',1,0);
vectorEccentricityHeavyTail = dlmread(filenameEccentricityHeavyTail,' ',1,0);
vectorMaxVertexDegreeHeavyTail = dlmread(filenameMaxVertexDegreeHeavyTail,' ',1,0);
vectorMinVertexDegreeHeavyTail = dlmread(filenameMinVertexDegreeHeavyTail,' ',1,0);
vectorAvgVertexDegreeHeavyTail = dlmread(filenameAvgVertexDegreeHeavyTail,' ',1,0);
vectorRadiusHeavyTail = dlmread(filenameRadiusHeavyTail,' ',1,0);
vectorMinCutHeavyTail = dlmread(filenameMinCutHeavyTail,' ',1,0);

%% vectors runtime
vectorGamintbRuntimeHeavyTail = dlmread(filenameGamintbRuntimeHeavyTail,' ',1,0);
vectorMintbRuntimeHeavyTail = dlmread(filenameMintbRuntimeHeavyTail,' ',1,0);