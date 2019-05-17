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
filenameGAMINTB = 'C:\Users\julia\OneDrive\Desktop\Projekt Algorithm Engineering Tests\ExtractedData\50-100\heuristics\gamintb.txt';
filenameMINTB = 'C:\Users\julia\OneDrive\Desktop\Projekt Algorithm Engineering Tests\ExtractedData\50-100\heuristics\mintb.txt';
% paths characteristics
%filenameDegeneracy = 'C:\Users\julia\OneDrive\Desktop\Projekt Algorithm Engineering Tests\ExtractedData\50-100\characteristics\degeneracy.txt';
%filenameDiameter = 'C:\Users\julia\OneDrive\Desktop\Projekt Algorithm Engineering Tests\ExtractedData\50-100\characteristics\diameter.txt';
%filenameEccentricity = 'C:\Users\julia\OneDrive\Desktop\Projekt Algorithm Engineering Tests\ExtractedData\50-100\characteristics\eccentricity.txt';
%filenameMaxVertexDegree = 'C:\Users\julia\OneDrive\Desktop\Projekt Algorithm Engineering Tests\ExtractedData\50-100\characteristics\maxVertexDegree.txt';
%filenameMinVertexDegree = 'C:\Users\julia\OneDrive\Desktop\Projekt Algorithm Engineering Tests\ExtractedData\50-100\characteristics\minVertexDegree.txt';
%filenameAvgVertexDegree = 'C:\Users\julia\OneDrive\Desktop\Projekt Algorithm Engineering Tests\ExtractedData\50-100\characteristics\avgVertexDegree.txt';
%filenameRadius = 'C:\Users\julia\OneDrive\Desktop\Projekt Algorithm Engineering Tests\ExtractedData\50-100\characteristics\Radius.txt';

% vectors heuristics
vectorGAMINTB = dlmread(filenameGAMINTB,' ',1,0);
vectorMINTB = dlmread(filenameMINTB,' ',1,0);
% vectors characteristics
%vectorDegeneracy = dlmread(filenameDegeneracy,' ',1,0);
%vectorDiameter = dlmread(filenameDiameter,' ',1,0);
%vectorEccentricity = dlmread(filenameEccentricity,' ',1,0);
%vectorMaxVertexDegree = dlmread(filenameMaxVertexDegree,' ',1,0);
%vectorMinVertexDegree = dlmread(filenameMinVertexDegree,' ',1,0);
%vectorAvgVertexDegree = dlmread(filenameAvgVertexDegree,' ',1,0);
%vectorRadius = dlmread(filenameRadius,' ',1,0);

%% --- some basic calculations
size1 = length(vectorGAMINTB);
size2 = length(vectorMINTB);

if size1 < size2
            tmp1 = zeros(1,(size2-size1));
            tmp2 = transpose(vectorGAMINTB);
            vectorGAMINTB = [tmp1 tmp2];
end
if size2 < size1
            tmp1 = zeros(1,(size1-size2));
            tmp2 = transpose(vectorMINTB);
            vectorMINTB = [tmp1 tmp2];
end

vectorDifference = [1,abs(vectorGAMINTB-transpose(vectorMINTB))]


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
title ('Differennce GAMINTB | MINTB', 'FontSize', 12)
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
