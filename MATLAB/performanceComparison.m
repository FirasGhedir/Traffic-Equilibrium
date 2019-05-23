% University Ulm
% Algorithm Engineering
% Evaluation
% @author Julian Bestler (julian.bestler@uniulm.de)
% @version 1.0


%% t-tests

% --- GridGraph ---
[t_oneSampleGridGraph, df_oneSampleGridGraph] = t_testOneSample(vectorGAMINTBGridGraph, vectorMINTBGridGraph, 'One Sample t-test GAMINTB | MINTB with Gridgraph')

% --- Poisson ---
% [t_oneSamplePoisson, df_oneSamplePoisson] = t_testOneSample(vectorGAMINTBPoisson, vectorMINTBPoisson, 'One Sample t-test GAMINTB | MINTB with Poisson')

% --- HeavyTail 
% [t_oneSampleHeavyTail, df_oneSampleHeavyTail] = t_testOneSample(vectorGAMINTBHeavyTail, vectorMINTBHeavyTail, 'One Sample t-test GAMINTB | MINTB with HeavyTail')


%% boxplots

% --- GridGraph ---

group = [ ones(size(vectorGamintbRuntimeGridGraph)); 2 * ones(size(vectorMintbRuntimeGridGraph))];
figure
boxplot([vectorGamintbRuntimeGridGraph; vectorMintbRuntimeGridGraph],group,'Labels',{'GAMINTB','MINTB'})
set(gca,'XTickLabel',{'GAMINTB','MINTB'})
title ('Runtime boxplots with Gridgraph', 'FontSize', 12)
ylabel('Time in [ms]')


% --- Poisson ---

% group = [ ones(size(vectorGamintbRuntimePoisson)); 2 * ones(size(vectorMintbRuntimePoisson))];
% figure
% boxplot([vectorGamintbRuntimePoisson; vectorMintbRuntimePoisson],group,'Labels',{'GAMINTB','MINTB'})
% set(gca,'XTickLabel',{'GAMINTB','MINTB'})
% title ('Runtime boxplots with Poisson', 'FontSize', 12)
% ylabel('Time in [ms]')


% --- HeavyTail 

% group = [ ones(size(vectorGamintbRuntimeHeavyTail)); 2 * ones(size(vectorMintbRuntimeHeavyTail))];
% figure
% boxplot([vectorGamintbRuntimeHeavyTail; vectorMintbRuntimeHeavyTail],group,'Labels',{'GAMINTB','MINTB'})
% set(gca,'XTickLabel',{'GAMINTB','MINTB'})
% title ('Runtime boxplots with HeavyTail', 'FontSize', 12)
% ylabel('Time in [ms]')