% University Ulm
% Algorithm Engineering
% Evaluation
% @author Julian Bestler (julian.bestler@uniulm.de)
% @version 1.0

%% global parameters

threshold = 0.05;

%%

% =============================== %
%             Runtime             %
% =============================== %

% vectors to compare with number of toolbooths as reference:
% ----------------------------------------------------------
%    ->  vectorDegeneracyGridGraph
%    ->  vectorDiameterGridGraph
%    ->  vectorEccentricityGridGraph
%    ->  vectorMaxVertexDegreeGridGraph
%    ->  vectorMinVertexDegreeGridGraph
%    ->  vectorAvgVertexDegreeGridGraph
%    ->  vectorRadiusGridGraph
%    ->  vectorMinCutGridGraph
%    ->  vectorGamintbRuntime

% plotRuntimeComparison(vectorGamintbRuntimeGridGraphSortedByGamintb, vectorGamintbRuntimePoissonSortedByGamintb, vectorGamintbRuntimeHeavyTailSortedByGamintb, 'Runtime (GAMINTB)',vectorMintbRuntimeGridGraphSortedByMintb, vectorMintbRuntimePoissonSortedByMintb, vectorMintbRuntimeHeavyTailSortedByMintb, 'Runtime (MINTB)')


%% t-tests
fprintf('\n=============== t-tests ===============\n\n-> threshold: %d \n\n', threshold)

% =============================== %
%            GridGraph            %
% =============================== %

% ----- t-test one sample -----

% [t_oneSampleGridGraph, df_oneSampleGridGraph] = t_testOneSample(vectorGAMINTBGridGraph, vectorMINTBGridGraph, 'One Sample t-test GAMINTB | MINTB with Gridgraph')
[h,p,ci,stats] = ttest(vectorGAMINTBGridGraph);
p_oneSampleTtest_GamintbGridgraph = p;

if p_oneSampleTtest_GamintbGridgraph < threshold
    fprintf('One sample t-test successful for GAMINTB with GridGraph: t = %d\n', p_oneSampleTtest_GamintbGridgraph)
else
    fprintf('One sample t-test was not successful for GAMINTB with GridGraph: t = %d\n', p_oneSampleTtest_GamintbGridgraph)
end
        
[h,p,ci,stats] = ttest(vectorMINTBGridGraph);
p_oneSampleTtest_MintbGridgraph = p;

if p_oneSampleTtest_MintbGridgraph < threshold
    fprintf('One sample t-test successful for MINTB with GridGraph: t = %d\n', p_oneSampleTtest_MintbGridgraph)
else
    fprintf('One sample t-test was not successful for MINTB with GridGraph: t = %d\n', p_oneSampleTtest_MintbGridgraph)
end
        
% ----- boxplots -----

group = [ ones(size(vectorGAMINTBGridGraph)); 2 * ones(size(vectorMINTBGridGraph))];
figure;
boxplot([vectorGAMINTBGridGraph; vectorMINTBGridGraph],group,'Labels',{'GAMINTB','MINTB'})
set(gca,'XTickLabel',{'GAMINTB','MINTB'})
title ('Boxplots with Gridgraph', 'FontSize', 12)
ylabel('Number of tollbooths')
grid on


%% 

% =============================== %
%             Poisson             %
% =============================== %

% ----- t-test one sample -----

% [t_oneSamplePoisson, df_oneSamplePoisson] = t_testOneSample(vectorGAMINTBPoisson, vectorMINTBPoisson, 'One Sample t-test GAMINTB | MINTB with Poisson')
[h,p,ci,stats] = ttest(vectorGAMINTBPoisson);
p_oneSampleTtest_GamintbPoisson = p;

if p_oneSampleTtest_GamintbPoisson < threshold
    fprintf('One sample t-test successful for GAMINTB with Poisson: t = %d\n', p_oneSampleTtest_GamintbPoisson)
else
    fprintf('One sample t-test was not successful for GAMINTB with Poisson: t = %d\n', p_oneSampleTtest_GamintbPoisson)
end
        
[h,p,ci,stats] = ttest(vectorMINTBPoisson);
p_oneSampleTtest_MintbPoisson = p;

if p_oneSampleTtest_MintbPoisson < threshold
    fprintf('One sample t-test successful for MINTB with Poisson: t = %d\n', p_oneSampleTtest_MintbPoisson)
else
    fprintf('One sample t-test was not successful for MINTB with Poisson: t = %d\n', p_oneSampleTtest_MintbPoisson)
end


% ----- boxplots -----

group = [ ones(size(vectorGAMINTBPoisson)); 2 * ones(size(vectorMINTBPoisson))];
figure;
boxplot([vectorGAMINTBPoisson; vectorMINTBPoisson],group,'Labels',{'GAMINTB','MINTB'})
set(gca,'XTickLabel',{'GAMINTB','MINTB'})
title ('Boxplots with Poisson Graph', 'FontSize', 12)
ylabel('Number of tollbooths')
grid on

group = [ ones(size(vectorGAMINTBPoissonEdited)); 2 * ones(size(vectorMINTBPoissonEdited))];
figure;
boxplot([vectorGAMINTBPoissonEdited; vectorMINTBPoissonEdited],group,'Labels',{'GAMINTB','MINTB'})
set(gca,'XTickLabel',{'GAMINTB','MINTB'})
title ('Boxplots with Poisson Graph (edited)', 'FontSize', 12)
ylabel('Number of tollbooths')
grid on

%% 

% =============================== %
%            HeavyTail            %
% =============================== %

% ----- t-test one sample -----

%[t_oneSampleHeavyTail, df_oneSampleHeavyTail] = t_testOneSample(vectorGAMINTBHeavyTail, vectorMINTBHeavyTail, 'One Sample t-test GAMINTB | MINTB with HeavyTail')
[h,p,ci,stats] = ttest(vectorGAMINTBHeavyTail);
p_oneSampleTtest_GamintbHeavyTail = p;

if p_oneSampleTtest_GamintbHeavyTail < threshold
    fprintf('One sample t-test successful for GAMINTB with HeavyTail: t = %d\n', p_oneSampleTtest_GamintbHeavyTail)
else
    fprintf('One sample t-test was not successful for GAMINTB with HeavyTail: t = %d\n', p_oneSampleTtest_GamintbHeavyTail)
end
        
[h,p,ci,stats] = ttest(vectorMINTBHeavyTail);
p_oneSampleTtest_MintbHeavyTail = p;

if p_oneSampleTtest_MintbHeavyTail < threshold
    fprintf('One sample t-test successful for MINTB with HeavyTail: t = %d\n', p_oneSampleTtest_MintbHeavyTail)
else
    fprintf('One sample t-test was not successful for MINTB with HeavyTail: t = %d\n', p_oneSampleTtest_MintbHeavyTail)
end
        
% ----- boxplots -----

group = [ ones(size(vectorGAMINTBHeavyTail)); 2 * ones(size(vectorMINTBHeavyTail))];
figure;
boxplot([vectorGAMINTBHeavyTail; vectorMINTBHeavyTail],group,'Labels',{'GAMINTB','MINTB'})
set(gca,'XTickLabel',{'GAMINTB','MINTB'})
title ('Boxplots with HeavyTail Graph', 'FontSize', 12)
ylabel('Number of tollbooths')
grid on

group = [ ones(size(vectorGAMINTBHeavyTailEdited)); 2 * ones(size(vectorMINTBHeavyTailEdited))];
figure;
boxplot([vectorGAMINTBHeavyTailEdited; vectorMINTBHeavyTailEdited],group,'Labels',{'GAMINTB','MINTB'})
set(gca,'XTickLabel',{'GAMINTB','MINTB'})
title ('Boxplots with HeavyTail Graph (edited)', 'FontSize', 12)
ylabel('Number of tollbooths')
grid on