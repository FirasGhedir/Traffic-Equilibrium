% University Ulm
% Algorithm Engineering
% Evaluation
% @author Julian Bestler (julian.bestler@uni-ulm.de)
% @version 1.0


%% threshold
threshold = 0.2;


%% Correlation coefficient
fprintf('\n=============== Correlation coefficient ===============\n\n-> threshold: %d \n\n', threshold)


% =============================== %
%           Comparison            %
% =============================== %


%% ---- Degeneracy ----
plotFeatureComparison(vectorDegeneracyGridGraphSortedByGamintb, vectorGamintbGridGraphSorted, vectorDegeneracyPoissonSortedByGamintb, vectorGamintbPoissonSorted, vectorDegeneracyHeavyTailSortedByGamintb, vectorGamintbHeavyTailSorted, 'Degeneracy (GAMINTB)', vectorDegeneracyGridGraphSortedByMintb, vectorMintbGridGraphSorted, vectorDegeneracyPoissonSortedByMintb, vectorMintbPoissonSorted, vectorDegeneracyHeavyTailSortedByMintb, vectorMintbHeavyTailSorted, 'Degeneracy (MINTB)', '/plots/featureDegeneracy.fig')

% GAMINTB
correlationCoefficient_Degeneracy_GridGraph_GAMINTB = CalculateCorrelationCoefficient(vectorDegeneracyGridGraphSortedByGamintb, vectorGamintbGridGraphSorted);
correlationCoefficient_Degeneracy_Poisson_GAMINTB = CalculateCorrelationCoefficient(vectorDegeneracyPoissonSortedByGamintb, vectorGamintbPoissonSorted);
correlationCoefficient_Degeneracy_HeavyTail_GAMINTB = CalculateCorrelationCoefficient(vectorDegeneracyHeavyTailSortedByGamintb, vectorGamintbHeavyTailSorted);

if correlationCoefficient_Degeneracy_GridGraph_GAMINTB > threshold
    fprintf('High dependency of Degeneracy with GAMINTB on GridGraph: r_correlation = %d\n', correlationCoefficient_Degeneracy_GridGraph_GAMINTB)
elseif correlationCoefficient_Degeneracy_Poisson_GAMINTB > threshold
    fprintf('High dependency of Degeneracy with GAMINTB on Poisson Graph: r_correlation = %d\n', correlationCoefficient_Degeneracy_Poisson_GAMINTB)
elseif correlationCoefficient_Degeneracy_HeavyTail_GAMINTB > threshold
    fprintf('High dependency of Degeneracy with GAMINTB on HeavyTail Graph: r_correlation = %d\n', correlationCoefficient_Degeneracy_HeavyTail_GAMINTB)
else
    disp('No significant dependency of Degeneracy with GAMINTB on any Graph.')
end

% MINTB
correlationCoefficient_Degeneracy_GridGraph_MINTB = CalculateCorrelationCoefficient(vectorDegeneracyGridGraphSortedByMintb, vectorMintbGridGraphSorted);
correlationCoefficient_Degeneracy_Poisson_MINTB = CalculateCorrelationCoefficient(vectorDegeneracyPoissonSortedByMintb, vectorMintbPoissonSorted);
correlationCoefficient_Degeneracy_HeavyTail_MINTB = CalculateCorrelationCoefficient(vectorDegeneracyHeavyTailSortedByMintb, vectorMintbHeavyTailSorted);

if correlationCoefficient_Degeneracy_GridGraph_MINTB > threshold
    fprintf('High dependency of Degeneracy with MINTB on GridGraph: r_correlation = %d\n', correlationCoefficient_Degeneracy_GridGraph_MINTB)
elseif correlationCoefficient_Degeneracy_Poisson_MINTB > threshold
    fprintf('High dependency of Degeneracy with MINTB on Poisson Graph: r_correlation = %d\n', correlationCoefficient_Degeneracy_Poisson_MINTB)
elseif correlationCoefficient_Degeneracy_HeavyTail_MINTB > threshold
    fprintf('High dependency of Degeneracy with MINTB on HeavyTail Graph: r_correlation = %d\n', correlationCoefficient_Degeneracy_HeavyTail_MINTB)
else
    disp('No significant dependency of Degeneracy with MINTB on any Graph.')
end

% ---- Degeneracy (edited )----
plotFeatureComparison(vectorDegeneracyGridGraphSortedByGamintb, vectorGamintbGridGraphSorted, vectorDegeneracyPoissonEditedSortedByGamintb, vectorGamintbPoissonEditedSorted, vectorDegeneracyHeavyTailEditedSortedByGamintb, vectorGamintbHeavyTailEditedSorted, 'Degeneracy (GAMINTB | edited)', vectorDegeneracyGridGraphSortedByMintb, vectorMintbGridGraphSorted, vectorDegeneracyPoissonEditedSortedByMintb, vectorMintbPoissonEditedSorted, vectorDegeneracyHeavyTailEditedSortedByMintb, vectorMintbHeavyTailEditedSorted, 'Degeneracy (MINTB | edited)', '/plots/featureDegeneracy.fig')

% GAMINTB
correlationCoefficient_Degeneracy_PoissonEdited_GAMINTB = CalculateCorrelationCoefficient(vectorDegeneracyPoissonEditedSortedByGamintb, vectorGamintbPoissonEditedSorted);
correlationCoefficient_Degeneracy_HeavyTailEdited_GAMINTB = CalculateCorrelationCoefficient(vectorDegeneracyHeavyTailEditedSortedByGamintb, vectorGamintbHeavyTailEditedSorted);

if correlationCoefficient_Degeneracy_PoissonEdited_GAMINTB > threshold
    fprintf('High dependency of Degeneracy with GAMINTB on PoissonEdited Graph: r_correlation = %d\n', correlationCoefficient_Degeneracy_PoissonEdited_GAMINTB)
elseif correlationCoefficient_Degeneracy_HeavyTailEdited_GAMINTB > threshold
    fprintf('High dependency of Degeneracy with GAMINTB on HeavyTailEdited Graph: r_correlation = %d\n', correlationCoefficient_Degeneracy_HeavyTailEdited_GAMINTB)
else
    disp('No significant dependency of Degeneracy with GAMINTB on any Graph.')
end

% MINTB
correlationCoefficient_Degeneracy_PoissonEdited_MINTB = CalculateCorrelationCoefficient(vectorDegeneracyPoissonEditedSortedByMintb, vectorMintbPoissonEditedSorted);
correlationCoefficient_Degeneracy_HeavyTailEdited_MINTB = CalculateCorrelationCoefficient(vectorDegeneracyHeavyTailEditedSortedByMintb, vectorMintbHeavyTailEditedSorted);

if correlationCoefficient_Degeneracy_PoissonEdited_MINTB > threshold
    fprintf('High dependency of Degeneracy with MINTB on PoissonEdited Graph: r_correlation = %d\n', correlationCoefficient_Degeneracy_PoissonEdited_MINTB)
elseif correlationCoefficient_Degeneracy_HeavyTailEdited_MINTB > threshold
    fprintf('High dependency of Degeneracy with MINTB on HeavyTailEdited Graph: r_correlation = %d\n', correlationCoefficient_Degeneracy_HeavyTailEdited_MINTB)
else
    disp('No significant dependency of Degeneracy with MINTB on any Graph.')
end


%% ---- Diameter ----
plotFeatureComparison(vectorDiameterGridGraphSortedByGamintb, vectorGamintbGridGraphSorted, vectorDiameterPoissonSortedByGamintb, vectorGamintbPoissonSorted, vectorDiameterHeavyTailSortedByGamintb, vectorGamintbHeavyTailSorted, 'Diameter (GAMINTB)', vectorDiameterGridGraphSortedByMintb, vectorMintbGridGraphSorted, vectorDiameterPoissonSortedByMintb, vectorMintbPoissonSorted, vectorDiameterHeavyTailSortedByMintb, vectorMintbHeavyTailSorted, 'Diameter (MINTB)', '/plots/featureDiameter.fig')

% GAMINTB
correlationCoefficient_Diameter_GridGraph_GAMINTB = CalculateCorrelationCoefficient(vectorDiameterGridGraphSortedByGamintb, vectorGamintbGridGraphSorted);
correlationCoefficient_Diameter_Poisson_GAMINTB = CalculateCorrelationCoefficient(vectorDiameterPoissonSortedByGamintb, vectorGamintbPoissonSorted);
correlationCoefficient_Diameter_HeavyTail_GAMINTB = CalculateCorrelationCoefficient(vectorDiameterHeavyTailSortedByGamintb, vectorGamintbHeavyTailSorted);

if correlationCoefficient_Diameter_GridGraph_GAMINTB > threshold
    fprintf('High dependency of Diameter with GAMINTB on GridGraph: r_correlation = %d\n', correlationCoefficient_Diameter_GridGraph_GAMINTB)
elseif correlationCoefficient_Diameter_Poisson_GAMINTB > threshold
    fprintf('High dependency of Diameter with GAMINTB on Poisson Graph: r_correlation = %d\n', correlationCoefficient_Diameter_Poisson_GAMINTB)
elseif correlationCoefficient_Diameter_HeavyTail_GAMINTB > threshold
    fprintf('High dependency of Diameter with GAMINTB on HeavyTail Graph: r_correlation = %d\n', correlationCoefficient_Diameter_HeavyTail_GAMINTB)
else
    disp('No significant dependency of Diameter with GAMINTB on any Graph.')
end

% MINTB
correlationCoefficient_Diameter_GridGraph_MINTB = CalculateCorrelationCoefficient(vectorDiameterGridGraphSortedByMintb, vectorMintbGridGraphSorted);
correlationCoefficient_Diameter_Poisson_MINTB = CalculateCorrelationCoefficient(vectorDiameterPoissonSortedByMintb, vectorMintbPoissonSorted);
correlationCoefficient_Diameter_HeavyTail_MINTB = CalculateCorrelationCoefficient(vectorDiameterHeavyTailSortedByMintb, vectorMintbHeavyTailSorted);

if correlationCoefficient_Diameter_GridGraph_MINTB > threshold
    fprintf('High dependency of Diameter with MINTB on GridGraph: r_correlation = %d\n', correlationCoefficient_Diameter_GridGraph_MINTB)
elseif correlationCoefficient_Diameter_Poisson_MINTB > threshold
    fprintf('High dependency of Diameter with MINTB on Poisson Graph: r_correlation = %d\n', correlationCoefficient_Diameter_Poisson_MINTB)
elseif correlationCoefficient_Diameter_HeavyTail_MINTB > threshold
    fprintf('High dependency of Diameter with MINTB on HeavyTail Graph: r_correlation = %d\n', correlationCoefficient_Diameter_HeavyTail_MINTB)
else
    disp('No significant dependency of Diameter with MINTB on any Graph.')
end

% ---- Diameter (edited )----
plotFeatureComparison(vectorDiameterGridGraphSortedByGamintb, vectorGamintbGridGraphSorted, vectorDiameterPoissonEditedSortedByGamintb, vectorGamintbPoissonEditedSorted, vectorDiameterHeavyTailEditedSortedByGamintb, vectorGamintbHeavyTailEditedSorted, 'Diameter (GAMINTB | edited)', vectorDiameterGridGraphSortedByMintb, vectorMintbGridGraphSorted, vectorDiameterPoissonEditedSortedByMintb, vectorMintbPoissonEditedSorted, vectorDiameterHeavyTailEditedSortedByMintb, vectorMintbHeavyTailEditedSorted, 'Diameter (MINTB | edited)', '/plots/featureDiameter.fig')

% GAMINTB
correlationCoefficient_Diameter_PoissonEdited_GAMINTB = CalculateCorrelationCoefficient(vectorDiameterPoissonEditedSortedByGamintb, vectorGamintbPoissonEditedSorted);
correlationCoefficient_Diameter_HeavyTailEdited_GAMINTB = CalculateCorrelationCoefficient(vectorDiameterHeavyTailEditedSortedByGamintb, vectorGamintbHeavyTailEditedSorted);

if correlationCoefficient_Diameter_PoissonEdited_GAMINTB > threshold
    fprintf('High dependency of Diameter with GAMINTB on PoissonEdited Graph: r_correlation = %d\n', correlationCoefficient_Diameter_PoissonEdited_GAMINTB)
elseif correlationCoefficient_Diameter_HeavyTailEdited_GAMINTB > threshold
    fprintf('High dependency of Diameter with GAMINTB on HeavyTailEdited Graph: r_correlation = %d\n', correlationCoefficient_Diameter_HeavyTailEdited_GAMINTB)
else
    disp('No significant dependency of Diameter with GAMINTB on any Graph.')
end

% MINTB
correlationCoefficient_Diameter_PoissonEdited_MINTB = CalculateCorrelationCoefficient(vectorDiameterPoissonEditedSortedByMintb, vectorMintbPoissonEditedSorted);
correlationCoefficient_Diameter_HeavyTailEdited_MINTB = CalculateCorrelationCoefficient(vectorDiameterHeavyTailEditedSortedByMintb, vectorMintbHeavyTailEditedSorted);

if correlationCoefficient_Diameter_PoissonEdited_MINTB > threshold
    fprintf('High dependency of Diameter with MINTB on PoissonEdited Graph: r_correlation = %d\n', correlationCoefficient_Diameter_PoissonEdited_MINTB)
elseif correlationCoefficient_Diameter_HeavyTailEdited_MINTB > threshold
    fprintf('High dependency of Diameter with MINTB on HeavyTailEdited Graph: r_correlation = %d\n', correlationCoefficient_Diameter_HeavyTailEdited_MINTB)
else
    disp('No significant dependency of Diameter with MINTB on any Graph.')
end


%% ---- Eccentricity ----
plotFeatureComparison(vectorEccentricityGridGraphSortedByGamintb, vectorGamintbGridGraphSorted, vectorEccentricityPoissonSortedByGamintb, vectorGamintbPoissonSorted, vectorEccentricityHeavyTailSortedByGamintb, vectorGamintbHeavyTailSorted, 'Eccentricity (GAMINTB)', vectorEccentricityGridGraphSortedByMintb, vectorMintbGridGraphSorted, vectorEccentricityPoissonSortedByMintb, vectorMintbPoissonSorted, vectorEccentricityHeavyTailSortedByMintb, vectorMintbHeavyTailSorted, 'Eccentricity (MINTB)', '/plots/featureEccentricity.fig')

% GAMINTB
correlationCoefficient_Eccentricity_GridGraph_GAMINTB = CalculateCorrelationCoefficient(vectorEccentricityGridGraphSortedByGamintb, vectorGamintbGridGraphSorted);
correlationCoefficient_Eccentricity_Poisson_GAMINTB = CalculateCorrelationCoefficient(vectorEccentricityPoissonSortedByGamintb, vectorGamintbPoissonSorted);
correlationCoefficient_Eccentricity_HeavyTail_GAMINTB = CalculateCorrelationCoefficient(vectorEccentricityHeavyTailSortedByGamintb, vectorGamintbHeavyTailSorted);

if correlationCoefficient_Eccentricity_GridGraph_GAMINTB > threshold
    fprintf('High dependency of Eccentricity with GAMINTB on GridGraph: r_correlation = %d\n', correlationCoefficient_Eccentricity_GridGraph_GAMINTB)
elseif correlationCoefficient_Eccentricity_Poisson_GAMINTB > threshold
    fprintf('High dependency of Eccentricity with GAMINTB on Poisson Graph: r_correlation = %d\n', correlationCoefficient_Eccentricity_Poisson_GAMINTB)
elseif correlationCoefficient_Eccentricity_HeavyTail_GAMINTB > threshold
    fprintf('High dependency of Eccentricity with GAMINTB on HeavyTail Graph: r_correlation = %d\n', correlationCoefficient_Eccentricity_HeavyTail_GAMINTB)
else
    disp('No significant dependency of Eccentricity with GAMINTB on any Graph.')
end

% MINTB
correlationCoefficient_Eccentricity_GridGraph_MINTB = CalculateCorrelationCoefficient(vectorEccentricityGridGraphSortedByMintb, vectorMintbGridGraphSorted);
correlationCoefficient_Eccentricity_Poisson_MINTB = CalculateCorrelationCoefficient(vectorEccentricityPoissonSortedByMintb, vectorMintbPoissonSorted);
correlationCoefficient_Eccentricity_HeavyTail_MINTB = CalculateCorrelationCoefficient(vectorEccentricityHeavyTailSortedByMintb, vectorMintbHeavyTailSorted);

if correlationCoefficient_Eccentricity_GridGraph_MINTB > threshold
    fprintf('High dependency of Eccentricity with MINTB on GridGraph: r_correlation = %d\n', correlationCoefficient_Eccentricity_GridGraph_MINTB)
elseif correlationCoefficient_Eccentricity_Poisson_MINTB > threshold
    fprintf('High dependency of Eccentricity with MINTB on Poisson Graph: r_correlation = %d\n', correlationCoefficient_Eccentricity_Poisson_MINTB)
elseif correlationCoefficient_Eccentricity_HeavyTail_MINTB > threshold
    fprintf('High dependency of Eccentricity with MINTB on HeavyTail Graph: r_correlation = %d\n', correlationCoefficient_Eccentricity_HeavyTail_MINTB)
else
    disp('No significant dependency of Eccentricity with MINTB on any Graph.')
end

% ---- Eccentricity (edited )----
plotFeatureComparison(vectorEccentricityGridGraphSortedByGamintb, vectorGamintbGridGraphSorted, vectorEccentricityPoissonEditedSortedByGamintb, vectorGamintbPoissonEditedSorted, vectorEccentricityHeavyTailEditedSortedByGamintb, vectorGamintbHeavyTailEditedSorted, 'Eccentricity (GAMINTB | edited)', vectorEccentricityGridGraphSortedByMintb, vectorMintbGridGraphSorted, vectorEccentricityPoissonEditedSortedByMintb, vectorMintbPoissonEditedSorted, vectorEccentricityHeavyTailEditedSortedByMintb, vectorMintbHeavyTailEditedSorted, 'Eccentricity (MINTB | edited)', '/plots/featureEccentricity.fig')

% GAMINTB
correlationCoefficient_Eccentricity_PoissonEdited_GAMINTB = CalculateCorrelationCoefficient(vectorEccentricityPoissonEditedSortedByGamintb, vectorGamintbPoissonEditedSorted);
correlationCoefficient_Eccentricity_HeavyTailEdited_GAMINTB = CalculateCorrelationCoefficient(vectorEccentricityHeavyTailEditedSortedByGamintb, vectorGamintbHeavyTailEditedSorted);

if correlationCoefficient_Eccentricity_PoissonEdited_GAMINTB > threshold
    fprintf('High dependency of Eccentricity with GAMINTB on PoissonEdited Graph: r_correlation = %d\n', correlationCoefficient_Eccentricity_PoissonEdited_GAMINTB)
elseif correlationCoefficient_Eccentricity_HeavyTailEdited_GAMINTB > threshold
    fprintf('High dependency of Eccentricity with GAMINTB on HeavyTailEdited Graph: r_correlation = %d\n', correlationCoefficient_Eccentricity_HeavyTailEdited_GAMINTB)
else
    disp('No significant dependency of Eccentricity with GAMINTB on any Graph.')
end

% MINTB
correlationCoefficient_Eccentricity_PoissonEdited_MINTB = CalculateCorrelationCoefficient(vectorEccentricityPoissonEditedSortedByMintb, vectorMintbPoissonEditedSorted);
correlationCoefficient_Eccentricity_HeavyTailEdited_MINTB = CalculateCorrelationCoefficient(vectorEccentricityHeavyTailEditedSortedByMintb, vectorMintbHeavyTailEditedSorted);

if correlationCoefficient_Eccentricity_PoissonEdited_MINTB > threshold
    fprintf('High dependency of Eccentricity with MINTB on PoissonEdited Graph: r_correlation = %d\n', correlationCoefficient_Eccentricity_PoissonEdited_MINTB)
elseif correlationCoefficient_Eccentricity_HeavyTailEdited_MINTB > threshold
    fprintf('High dependency of Eccentricity with MINTB on HeavyTailEdited Graph: r_correlation = %d\n', correlationCoefficient_Eccentricity_HeavyTailEdited_MINTB)
else
    disp('No significant dependency of Eccentricity with MINTB on any Graph.')
end


%% ---- MaxVertexDegree ----
plotFeatureComparison(vectorMaxVertexDegreeGridGraphSortedByGamintb, vectorGamintbGridGraphSorted, vectorMaxVertexDegreePoissonSortedByGamintb, vectorGamintbPoissonSorted, vectorMaxVertexDegreeHeavyTailSortedByGamintb, vectorGamintbHeavyTailSorted, 'MaxVertexDegree (GAMINTB)', vectorMaxVertexDegreeGridGraphSortedByMintb, vectorMintbGridGraphSorted, vectorMaxVertexDegreePoissonSortedByMintb, vectorMintbPoissonSorted, vectorMaxVertexDegreeHeavyTailSortedByMintb, vectorMintbHeavyTailSorted, 'MaxVertexDegree (MINTB)', '/plots/featureMaxVertexDegree.fig')

% GAMINTB
correlationCoefficient_MaxVertexDegree_GridGraph_GAMINTB = CalculateCorrelationCoefficient(vectorMaxVertexDegreeGridGraphSortedByGamintb, vectorGamintbGridGraphSorted);
correlationCoefficient_MaxVertexDegree_Poisson_GAMINTB = CalculateCorrelationCoefficient(vectorMaxVertexDegreePoissonSortedByGamintb, vectorGamintbPoissonSorted);
correlationCoefficient_MaxVertexDegree_HeavyTail_GAMINTB = CalculateCorrelationCoefficient(vectorMaxVertexDegreeHeavyTailSortedByGamintb, vectorGamintbHeavyTailSorted);

if correlationCoefficient_MaxVertexDegree_GridGraph_GAMINTB > threshold
    fprintf('High dependency of MaxVertexDegree with GAMINTB on GridGraph: r_correlation = %d\n', correlationCoefficient_MaxVertexDegree_GridGraph_GAMINTB)
elseif correlationCoefficient_MaxVertexDegree_Poisson_GAMINTB > threshold
    fprintf('High dependency of MaxVertexDegree with GAMINTB on Poisson Graph: r_correlation = %d\n', correlationCoefficient_MaxVertexDegree_Poisson_GAMINTB)
elseif correlationCoefficient_MaxVertexDegree_HeavyTail_GAMINTB > threshold
    fprintf('High dependency of MaxVertexDegree with GAMINTB on HeavyTail Graph: r_correlation = %d\n', correlationCoefficient_MaxVertexDegree_HeavyTail_GAMINTB)
else
    disp('No significant dependency of MaxVertexDegree with GAMINTB on any Graph.')
end

% MINTB
correlationCoefficient_MaxVertexDegree_GridGraph_MINTB = CalculateCorrelationCoefficient(vectorMaxVertexDegreeGridGraphSortedByMintb, vectorMintbGridGraphSorted);
correlationCoefficient_MaxVertexDegree_Poisson_MINTB = CalculateCorrelationCoefficient(vectorMaxVertexDegreePoissonSortedByMintb, vectorMintbPoissonSorted);
correlationCoefficient_MaxVertexDegree_HeavyTail_MINTB = CalculateCorrelationCoefficient(vectorMaxVertexDegreeHeavyTailSortedByMintb, vectorMintbHeavyTailSorted);

if correlationCoefficient_MaxVertexDegree_GridGraph_MINTB > threshold
    fprintf('High dependency of MaxVertexDegree with MINTB on GridGraph: r_correlation = %d\n', correlationCoefficient_MaxVertexDegree_GridGraph_MINTB)
elseif correlationCoefficient_MaxVertexDegree_Poisson_MINTB > threshold
    fprintf('High dependency of MaxVertexDegree with MINTB on Poisson Graph: r_correlation = %d\n', correlationCoefficient_MaxVertexDegree_Poisson_MINTB)
elseif correlationCoefficient_MaxVertexDegree_HeavyTail_MINTB > threshold
    fprintf('High dependency of MaxVertexDegree with MINTB on HeavyTail Graph: r_correlation = %d\n', correlationCoefficient_MaxVertexDegree_HeavyTail_MINTB)
else
    disp('No significant dependency of MaxVertexDegree with MINTB on any Graph.')
end

% ---- MaxVertexDegree (edited )----
plotFeatureComparison(vectorMaxVertexDegreeGridGraphSortedByGamintb, vectorGamintbGridGraphSorted, vectorMaxVertexDegreePoissonEditedSortedByGamintb, vectorGamintbPoissonEditedSorted, vectorMaxVertexDegreeHeavyTailEditedSortedByGamintb, vectorGamintbHeavyTailEditedSorted, 'MaxVertexDegree (GAMINTB | edited)', vectorMaxVertexDegreeGridGraphSortedByMintb, vectorMintbGridGraphSorted, vectorMaxVertexDegreePoissonEditedSortedByMintb, vectorMintbPoissonEditedSorted, vectorMaxVertexDegreeHeavyTailEditedSortedByMintb, vectorMintbHeavyTailEditedSorted, 'MaxVertexDegree (MINTB | edited)', '/plots/featureMaxVertexDegree.fig')

% GAMINTB
correlationCoefficient_MaxVertexDegree_PoissonEdited_GAMINTB = CalculateCorrelationCoefficient(vectorMaxVertexDegreePoissonEditedSortedByGamintb, vectorGamintbPoissonEditedSorted);
correlationCoefficient_MaxVertexDegree_HeavyTailEdited_GAMINTB = CalculateCorrelationCoefficient(vectorMaxVertexDegreeHeavyTailEditedSortedByGamintb, vectorGamintbHeavyTailEditedSorted);

if correlationCoefficient_MaxVertexDegree_PoissonEdited_GAMINTB > threshold
    fprintf('High dependency of MaxVertexDegree with GAMINTB on PoissonEdited Graph: r_correlation = %d\n', correlationCoefficient_MaxVertexDegree_PoissonEdited_GAMINTB)
elseif correlationCoefficient_MaxVertexDegree_HeavyTailEdited_GAMINTB > threshold
    fprintf('High dependency of MaxVertexDegree with GAMINTB on HeavyTailEdited Graph: r_correlation = %d\n', correlationCoefficient_MaxVertexDegree_HeavyTailEdited_GAMINTB)
else
    disp('No significant dependency of MaxVertexDegree with GAMINTB on any Graph.')
end

% MINTB
correlationCoefficient_MaxVertexDegree_PoissonEdited_MINTB = CalculateCorrelationCoefficient(vectorMaxVertexDegreePoissonEditedSortedByMintb, vectorMintbPoissonEditedSorted);
correlationCoefficient_MaxVertexDegree_HeavyTailEdited_MINTB = CalculateCorrelationCoefficient(vectorMaxVertexDegreeHeavyTailEditedSortedByMintb, vectorMintbHeavyTailEditedSorted);

if correlationCoefficient_MaxVertexDegree_PoissonEdited_MINTB > threshold
    fprintf('High dependency of MaxVertexDegree with MINTB on PoissonEdited Graph: r_correlation = %d\n', correlationCoefficient_MaxVertexDegree_PoissonEdited_MINTB)
elseif correlationCoefficient_MaxVertexDegree_HeavyTailEdited_MINTB > threshold
    fprintf('High dependency of MaxVertexDegree with MINTB on HeavyTailEdited Graph: r_correlation = %d\n', correlationCoefficient_MaxVertexDegree_HeavyTailEdited_MINTB)
else
    disp('No significant dependency of MaxVertexDegree with MINTB on any Graph.')
end


%% ---- MinVertexDegree ----
plotFeatureComparison(vectorMinVertexDegreeGridGraphSortedByGamintb, vectorGamintbGridGraphSorted, vectorMinVertexDegreePoissonSortedByGamintb, vectorGamintbPoissonSorted, vectorMinVertexDegreeHeavyTailSortedByGamintb, vectorGamintbHeavyTailSorted, 'MinVertexDegree (GAMINTB)', vectorMinVertexDegreeGridGraphSortedByMintb, vectorMintbGridGraphSorted, vectorMinVertexDegreePoissonSortedByMintb, vectorMintbPoissonSorted, vectorMinVertexDegreeHeavyTailSortedByMintb, vectorMintbHeavyTailSorted, 'MinVertexDegree (MINTB)', '/plots/featureMinVertexDegree.fig')

% GAMINTB
correlationCoefficient_MinVertexDegree_GridGraph_GAMINTB = CalculateCorrelationCoefficient(vectorMinVertexDegreeGridGraphSortedByGamintb, vectorGamintbGridGraphSorted);
correlationCoefficient_MinVertexDegree_Poisson_GAMINTB = CalculateCorrelationCoefficient(vectorMinVertexDegreePoissonSortedByGamintb, vectorGamintbPoissonSorted);
correlationCoefficient_MinVertexDegree_HeavyTail_GAMINTB = CalculateCorrelationCoefficient(vectorMinVertexDegreeHeavyTailSortedByGamintb, vectorGamintbHeavyTailSorted);

if correlationCoefficient_MinVertexDegree_GridGraph_GAMINTB > threshold
    fprintf('High dependency of MinVertexDegree with GAMINTB on GridGraph: r_correlation = %d\n', correlationCoefficient_MinVertexDegree_GridGraph_GAMINTB)
elseif correlationCoefficient_MinVertexDegree_Poisson_GAMINTB > threshold
    fprintf('High dependency of MinVertexDegree with GAMINTB on Poisson Graph: r_correlation = %d\n', correlationCoefficient_MinVertexDegree_Poisson_GAMINTB)
elseif correlationCoefficient_MinVertexDegree_HeavyTail_GAMINTB > threshold
    fprintf('High dependency of MinVertexDegree with GAMINTB on HeavyTail Graph: r_correlation = %d\n', correlationCoefficient_MinVertexDegree_HeavyTail_GAMINTB)
else
    disp('No significant dependency of MinVertexDegree with GAMINTB on any Graph.')
end

% MINTB
correlationCoefficient_MinVertexDegree_GridGraph_MINTB = CalculateCorrelationCoefficient(vectorMinVertexDegreeGridGraphSortedByMintb, vectorMintbGridGraphSorted);
correlationCoefficient_MinVertexDegree_Poisson_MINTB = CalculateCorrelationCoefficient(vectorMinVertexDegreePoissonSortedByMintb, vectorMintbPoissonSorted);
correlationCoefficient_MinVertexDegree_HeavyTail_MINTB = CalculateCorrelationCoefficient(vectorMinVertexDegreeHeavyTailSortedByMintb, vectorMintbHeavyTailSorted);

if correlationCoefficient_MinVertexDegree_GridGraph_MINTB > threshold
    fprintf('High dependency of MinVertexDegree with MINTB on GridGraph: r_correlation = %d\n', correlationCoefficient_MinVertexDegree_GridGraph_MINTB)
elseif correlationCoefficient_MinVertexDegree_Poisson_MINTB > threshold
    fprintf('High dependency of MinVertexDegree with MINTB on Poisson Graph: r_correlation = %d\n', correlationCoefficient_MinVertexDegree_Poisson_MINTB)
elseif correlationCoefficient_MinVertexDegree_HeavyTail_MINTB > threshold
    fprintf('High dependency of MinVertexDegree with MINTB on HeavyTail Graph: r_correlation = %d\n', correlationCoefficient_MinVertexDegree_HeavyTail_MINTB)
else
    disp('No significant dependency of MinVertexDegree with MINTB on any Graph.')
end

% ---- MinVertexDegree (edited )----
plotFeatureComparison(vectorMinVertexDegreeGridGraphSortedByGamintb, vectorGamintbGridGraphSorted, vectorMinVertexDegreePoissonEditedSortedByGamintb, vectorGamintbPoissonEditedSorted, vectorMinVertexDegreeHeavyTailEditedSortedByGamintb, vectorGamintbHeavyTailEditedSorted, 'MinVertexDegree (GAMINTB | edited)', vectorMinVertexDegreeGridGraphSortedByMintb, vectorMintbGridGraphSorted, vectorMinVertexDegreePoissonEditedSortedByMintb, vectorMintbPoissonEditedSorted, vectorMinVertexDegreeHeavyTailEditedSortedByMintb, vectorMintbHeavyTailEditedSorted, 'MinVertexDegree (MINTB | edited)', '/plots/featureMinVertexDegree.fig')

% GAMINTB
correlationCoefficient_MinVertexDegree_PoissonEdited_GAMINTB = CalculateCorrelationCoefficient(vectorMinVertexDegreePoissonEditedSortedByGamintb, vectorGamintbPoissonEditedSorted);
correlationCoefficient_MinVertexDegree_HeavyTailEdited_GAMINTB = CalculateCorrelationCoefficient(vectorMinVertexDegreeHeavyTailEditedSortedByGamintb, vectorGamintbHeavyTailEditedSorted);

if correlationCoefficient_MinVertexDegree_PoissonEdited_GAMINTB > threshold
    fprintf('High dependency of MinVertexDegree with GAMINTB on PoissonEdited Graph: r_correlation = %d\n', correlationCoefficient_MinVertexDegree_PoissonEdited_GAMINTB)
elseif correlationCoefficient_MinVertexDegree_HeavyTailEdited_GAMINTB > threshold
    fprintf('High dependency of MinVertexDegree with GAMINTB on HeavyTailEdited Graph: r_correlation = %d\n', correlationCoefficient_MinVertexDegree_HeavyTailEdited_GAMINTB)
else
    disp('No significant dependency of MinVertexDegree with GAMINTB on any Graph.')
end

% MINTB
correlationCoefficient_MinVertexDegree_PoissonEdited_MINTB = CalculateCorrelationCoefficient(vectorMinVertexDegreePoissonEditedSortedByMintb, vectorMintbPoissonEditedSorted);
correlationCoefficient_MinVertexDegree_HeavyTailEdited_MINTB = CalculateCorrelationCoefficient(vectorMinVertexDegreeHeavyTailEditedSortedByMintb, vectorMintbHeavyTailEditedSorted);

if correlationCoefficient_MinVertexDegree_PoissonEdited_MINTB > threshold
    fprintf('High dependency of MinVertexDegree with MINTB on PoissonEdited Graph: r_correlation = %d\n', correlationCoefficient_MinVertexDegree_PoissonEdited_MINTB)
elseif correlationCoefficient_MinVertexDegree_HeavyTailEdited_MINTB > threshold
    fprintf('High dependency of MinVertexDegree with MINTB on HeavyTailEdited Graph: r_correlation = %d\n', correlationCoefficient_MinVertexDegree_HeavyTailEdited_MINTB)
else
    disp('No significant dependency of MinVertexDegree with MINTB on any Graph.')
end


%% ---- AvgVertexDegree ----
plotFeatureComparison(vectorAvgVertexDegreeGridGraphSortedByGamintb, vectorGamintbGridGraphSorted, vectorAvgVertexDegreePoissonSortedByGamintb, vectorGamintbPoissonSorted, vectorAvgVertexDegreeHeavyTailSortedByGamintb, vectorGamintbHeavyTailSorted, 'AvgVertexDegree (GAMINTB)', vectorAvgVertexDegreeGridGraphSortedByMintb, vectorMintbGridGraphSorted, vectorAvgVertexDegreePoissonSortedByMintb, vectorMintbPoissonSorted, vectorAvgVertexDegreeHeavyTailSortedByMintb, vectorMintbHeavyTailSorted, 'AvgVertexDegree (MINTB)', '/plots/featureAvgVertexDegree.fig')

% GAMINTB
correlationCoefficient_AvgVertexDegree_GridGraph_GAMINTB = CalculateCorrelationCoefficient(vectorAvgVertexDegreeGridGraphSortedByGamintb, vectorGamintbGridGraphSorted);
correlationCoefficient_AvgVertexDegree_Poisson_GAMINTB = CalculateCorrelationCoefficient(vectorAvgVertexDegreePoissonSortedByGamintb, vectorGamintbPoissonSorted);
correlationCoefficient_AvgVertexDegree_HeavyTail_GAMINTB = CalculateCorrelationCoefficient(vectorAvgVertexDegreeHeavyTailSortedByGamintb, vectorGamintbHeavyTailSorted);

if correlationCoefficient_AvgVertexDegree_GridGraph_GAMINTB > threshold
    fprintf('High dependency of AvgVertexDegree with GAMINTB on GridGraph: r_correlation = %d\n', correlationCoefficient_AvgVertexDegree_GridGraph_GAMINTB)
elseif correlationCoefficient_AvgVertexDegree_Poisson_GAMINTB > threshold
    fprintf('High dependency of AvgVertexDegree with GAMINTB on Poisson Graph: r_correlation = %d\n', correlationCoefficient_AvgVertexDegree_Poisson_GAMINTB)
elseif correlationCoefficient_AvgVertexDegree_HeavyTail_GAMINTB > threshold
    fprintf('High dependency of AvgVertexDegree with GAMINTB on HeavyTail Graph: r_correlation = %d\n', correlationCoefficient_AvgVertexDegree_HeavyTail_GAMINTB)
else
    disp('No significant dependency of AvgVertexDegree with GAMINTB on any Graph.')
end

% MINTB
correlationCoefficient_AvgVertexDegree_GridGraph_MINTB = CalculateCorrelationCoefficient(vectorAvgVertexDegreeGridGraphSortedByMintb, vectorMintbGridGraphSorted);
correlationCoefficient_AvgVertexDegree_Poisson_MINTB = CalculateCorrelationCoefficient(vectorAvgVertexDegreePoissonSortedByMintb, vectorMintbPoissonSorted);
correlationCoefficient_AvgVertexDegree_HeavyTail_MINTB = CalculateCorrelationCoefficient(vectorAvgVertexDegreeHeavyTailSortedByMintb, vectorMintbHeavyTailSorted);

if correlationCoefficient_AvgVertexDegree_GridGraph_MINTB > threshold
    fprintf('High dependency of AvgVertexDegree with MINTB on GridGraph: r_correlation = %d\n', correlationCoefficient_AvgVertexDegree_GridGraph_MINTB)
elseif correlationCoefficient_AvgVertexDegree_Poisson_MINTB > threshold
    fprintf('High dependency of AvgVertexDegree with MINTB on Poisson Graph: r_correlation = %d\n', correlationCoefficient_AvgVertexDegree_Poisson_MINTB)
elseif correlationCoefficient_AvgVertexDegree_HeavyTail_MINTB > threshold
    fprintf('High dependency of AvgVertexDegree with MINTB on HeavyTail Graph: r_correlation = %d\n', correlationCoefficient_AvgVertexDegree_HeavyTail_MINTB)
else
    disp('No significant dependency of AvgVertexDegree with MINTB on any Graph.')
end

% ---- AvgVertexDegree (edited )----
plotFeatureComparison(vectorAvgVertexDegreeGridGraphSortedByGamintb, vectorGamintbGridGraphSorted, vectorAvgVertexDegreePoissonEditedSortedByGamintb, vectorGamintbPoissonEditedSorted, vectorAvgVertexDegreeHeavyTailEditedSortedByGamintb, vectorGamintbHeavyTailEditedSorted, 'AvgVertexDegree (GAMINTB | edited)', vectorAvgVertexDegreeGridGraphSortedByMintb, vectorMintbGridGraphSorted, vectorAvgVertexDegreePoissonEditedSortedByMintb, vectorMintbPoissonEditedSorted, vectorAvgVertexDegreeHeavyTailEditedSortedByMintb, vectorMintbHeavyTailEditedSorted, 'AvgVertexDegree (MINTB | edited)', '/plots/featureAvgVertexDegree.fig')

% GAMINTB
correlationCoefficient_AvgVertexDegree_PoissonEdited_GAMINTB = CalculateCorrelationCoefficient(vectorAvgVertexDegreePoissonEditedSortedByGamintb, vectorGamintbPoissonEditedSorted);
correlationCoefficient_AvgVertexDegree_HeavyTailEdited_GAMINTB = CalculateCorrelationCoefficient(vectorAvgVertexDegreeHeavyTailEditedSortedByGamintb, vectorGamintbHeavyTailEditedSorted);

if correlationCoefficient_AvgVertexDegree_PoissonEdited_GAMINTB > threshold
    fprintf('High dependency of AvgVertexDegree with GAMINTB on PoissonEdited Graph: r_correlation = %d\n', correlationCoefficient_AvgVertexDegree_PoissonEdited_GAMINTB)
elseif correlationCoefficient_AvgVertexDegree_HeavyTailEdited_GAMINTB > threshold
    fprintf('High dependency of AvgVertexDegree with GAMINTB on HeavyTailEdited Graph: r_correlation = %d\n', correlationCoefficient_AvgVertexDegree_HeavyTailEdited_GAMINTB)
else
    disp('No significant dependency of AvgVertexDegree with GAMINTB on any Graph.')
end

% MINTB
correlationCoefficient_AvgVertexDegree_PoissonEdited_MINTB = CalculateCorrelationCoefficient(vectorAvgVertexDegreePoissonEditedSortedByMintb, vectorMintbPoissonEditedSorted);
correlationCoefficient_AvgVertexDegree_HeavyTailEdited_MINTB = CalculateCorrelationCoefficient(vectorAvgVertexDegreeHeavyTailEditedSortedByMintb, vectorMintbHeavyTailEditedSorted);

if correlationCoefficient_AvgVertexDegree_PoissonEdited_MINTB > threshold
    fprintf('High dependency of AvgVertexDegree with MINTB on PoissonEdited Graph: r_correlation = %d\n', correlationCoefficient_AvgVertexDegree_PoissonEdited_MINTB)
elseif correlationCoefficient_AvgVertexDegree_HeavyTailEdited_MINTB > threshold
    fprintf('High dependency of AvgVertexDegree with MINTB on HeavyTailEdited Graph: r_correlation = %d\n', correlationCoefficient_AvgVertexDegree_HeavyTailEdited_MINTB)
else
    disp('No significant dependency of AvgVertexDegree with MINTB on any Graph.')
end


%% ---- Radius ----
plotFeatureComparison(vectorRadiusGridGraphSortedByGamintb, vectorGamintbGridGraphSorted, vectorRadiusPoissonSortedByGamintb, vectorGamintbPoissonSorted, vectorRadiusHeavyTailSortedByGamintb, vectorGamintbHeavyTailSorted, 'Radius (GAMINTB)', vectorRadiusGridGraphSortedByMintb, vectorMintbGridGraphSorted, vectorRadiusPoissonSortedByMintb, vectorMintbPoissonSorted, vectorRadiusHeavyTailSortedByMintb, vectorMintbHeavyTailSorted, 'Radius (MINTB)', '/plots/featureRadius.fig')

% GAMINTB
correlationCoefficient_Radius_GridGraph_GAMINTB = CalculateCorrelationCoefficient(vectorRadiusGridGraphSortedByGamintb, vectorGamintbGridGraphSorted);
correlationCoefficient_Radius_Poisson_GAMINTB = CalculateCorrelationCoefficient(vectorRadiusPoissonSortedByGamintb, vectorGamintbPoissonSorted);
correlationCoefficient_Radius_HeavyTail_GAMINTB = CalculateCorrelationCoefficient(vectorRadiusHeavyTailSortedByGamintb, vectorGamintbHeavyTailSorted);

if correlationCoefficient_Radius_GridGraph_GAMINTB > threshold
    fprintf('High dependency of Radius with GAMINTB on GridGraph: r_correlation = %d\n', correlationCoefficient_Radius_GridGraph_GAMINTB)
elseif correlationCoefficient_Radius_Poisson_GAMINTB > threshold
    fprintf('High dependency of Radius with GAMINTB on Poisson Graph: r_correlation = %d\n', correlationCoefficient_Radius_Poisson_GAMINTB)
elseif correlationCoefficient_Radius_HeavyTail_GAMINTB > threshold
    fprintf('High dependency of Radius with GAMINTB on HeavyTail Graph: r_correlation = %d\n', correlationCoefficient_Radius_HeavyTail_GAMINTB)
else
    disp('No significant dependency of Radius with GAMINTB on any Graph.')
end

% MINTB
correlationCoefficient_Radius_GridGraph_MINTB = CalculateCorrelationCoefficient(vectorRadiusGridGraphSortedByMintb, vectorMintbGridGraphSorted);
correlationCoefficient_Radius_Poisson_MINTB = CalculateCorrelationCoefficient(vectorRadiusPoissonSortedByMintb, vectorMintbPoissonSorted);
correlationCoefficient_Radius_HeavyTail_MINTB = CalculateCorrelationCoefficient(vectorRadiusHeavyTailSortedByMintb, vectorMintbHeavyTailSorted);

if correlationCoefficient_Radius_GridGraph_MINTB > threshold
    fprintf('High dependency of Radius with MINTB on GridGraph: r_correlation = %d\n', correlationCoefficient_Radius_GridGraph_MINTB)
elseif correlationCoefficient_Radius_Poisson_MINTB > threshold
    fprintf('High dependency of Radius with MINTB on Poisson Graph: r_correlation = %d\n', correlationCoefficient_Radius_Poisson_MINTB)
elseif correlationCoefficient_Radius_HeavyTail_MINTB > threshold
    fprintf('High dependency of Radius with MINTB on HeavyTail Graph: r_correlation = %d\n', correlationCoefficient_Radius_HeavyTail_MINTB)
else
    disp('No significant dependency of Radius with MINTB on any Graph.')
end

% ---- Radius (edited )----
plotFeatureComparison(vectorRadiusGridGraphSortedByGamintb, vectorGamintbGridGraphSorted, vectorRadiusPoissonEditedSortedByGamintb, vectorGamintbPoissonEditedSorted, vectorRadiusHeavyTailEditedSortedByGamintb, vectorGamintbHeavyTailEditedSorted, 'Radius (GAMINTB | edited)', vectorRadiusGridGraphSortedByMintb, vectorMintbGridGraphSorted, vectorRadiusPoissonEditedSortedByMintb, vectorMintbPoissonEditedSorted, vectorRadiusHeavyTailEditedSortedByMintb, vectorMintbHeavyTailEditedSorted, 'Radius (MINTB | edited)', '/plots/featureRadius.fig')

% GAMINTB
correlationCoefficient_Radius_PoissonEdited_GAMINTB = CalculateCorrelationCoefficient(vectorRadiusPoissonEditedSortedByGamintb, vectorGamintbPoissonEditedSorted);
correlationCoefficient_Radius_HeavyTailEdited_GAMINTB = CalculateCorrelationCoefficient(vectorRadiusHeavyTailEditedSortedByGamintb, vectorGamintbHeavyTailEditedSorted);

if correlationCoefficient_Radius_PoissonEdited_GAMINTB > threshold
    fprintf('High dependency of Radius with GAMINTB on PoissonEdited Graph: r_correlation = %d\n', correlationCoefficient_Radius_PoissonEdited_GAMINTB)
elseif correlationCoefficient_Radius_HeavyTailEdited_GAMINTB > threshold
    fprintf('High dependency of Radius with GAMINTB on HeavyTailEdited Graph: r_correlation = %d\n', correlationCoefficient_Radius_HeavyTailEdited_GAMINTB)
else
    disp('No significant dependency of Radius with GAMINTB on any Graph.')
end

% MINTB
correlationCoefficient_Radius_PoissonEdited_MINTB = CalculateCorrelationCoefficient(vectorRadiusPoissonEditedSortedByMintb, vectorMintbPoissonEditedSorted);
correlationCoefficient_Radius_HeavyTailEdited_MINTB = CalculateCorrelationCoefficient(vectorRadiusHeavyTailEditedSortedByMintb, vectorMintbHeavyTailEditedSorted);

if correlationCoefficient_Radius_PoissonEdited_MINTB > threshold
    fprintf('High dependency of Radius with MINTB on PoissonEdited Graph: r_correlation = %d\n', correlationCoefficient_Radius_PoissonEdited_MINTB)
elseif correlationCoefficient_Radius_HeavyTailEdited_MINTB > threshold
    fprintf('High dependency of Radius with MINTB on HeavyTailEdited Graph: r_correlation = %d\n', correlationCoefficient_Radius_HeavyTailEdited_MINTB)
else
    disp('No significant dependency of Radius with MINTB on any Graph.')
end


%% ---- MinCut ----
plotFeatureComparison(vectorMinCutGridGraphSortedByGamintb, vectorGamintbGridGraphSorted, vectorMinCutPoissonSortedByGamintb, vectorGamintbPoissonSorted, vectorMinCutHeavyTailSortedByGamintb, vectorGamintbHeavyTailSorted, 'MinCut (GAMINTB)', vectorMinCutGridGraphSortedByMintb, vectorMintbGridGraphSorted, vectorMinCutPoissonSortedByMintb, vectorMintbPoissonSorted, vectorMinCutHeavyTailSortedByMintb, vectorMintbHeavyTailSorted, 'MinCut (MINTB)', '/plots/featureMinCut.fig')

% GAMINTB
correlationCoefficient_MinCut_GridGraph_GAMINTB = CalculateCorrelationCoefficient(vectorMinCutGridGraphSortedByGamintb, vectorGamintbGridGraphSorted);
correlationCoefficient_MinCut_Poisson_GAMINTB = CalculateCorrelationCoefficient(vectorMinCutPoissonSortedByGamintb, vectorGamintbPoissonSorted);
correlationCoefficient_MinCut_HeavyTail_GAMINTB = CalculateCorrelationCoefficient(vectorMinCutHeavyTailSortedByGamintb, vectorGamintbHeavyTailSorted);

if correlationCoefficient_MinCut_GridGraph_GAMINTB > threshold
    fprintf('High dependency of MinCut with GAMINTB on GridGraph: r_correlation = %d\n', correlationCoefficient_MinCut_GridGraph_GAMINTB)
elseif correlationCoefficient_MinCut_Poisson_GAMINTB > threshold
    fprintf('High dependency of MinCut with GAMINTB on Poisson Graph: r_correlation = %d\n', correlationCoefficient_MinCut_Poisson_GAMINTB)
elseif correlationCoefficient_MinCut_HeavyTail_GAMINTB > threshold
    fprintf('High dependency of MinCut with GAMINTB on HeavyTail Graph: r_correlation = %d\n', correlationCoefficient_MinCut_HeavyTail_GAMINTB)
else
    disp('No significant dependency of MinCut with GAMINTB on any Graph.')
end

% MINTB
correlationCoefficient_MinCut_GridGraph_MINTB = CalculateCorrelationCoefficient(vectorMinCutGridGraphSortedByMintb, vectorMintbGridGraphSorted);
correlationCoefficient_MinCut_Poisson_MINTB = CalculateCorrelationCoefficient(vectorMinCutPoissonSortedByMintb, vectorMintbPoissonSorted);
correlationCoefficient_MinCut_HeavyTail_MINTB = CalculateCorrelationCoefficient(vectorMinCutHeavyTailSortedByMintb, vectorMintbHeavyTailSorted);

if correlationCoefficient_MinCut_GridGraph_MINTB > threshold
    fprintf('High dependency of MinCut with MINTB on GridGraph: r_correlation = %d\n', correlationCoefficient_MinCut_GridGraph_MINTB)
elseif correlationCoefficient_MinCut_Poisson_MINTB > threshold
    fprintf('High dependency of MinCut with MINTB on Poisson Graph: r_correlation = %d\n', correlationCoefficient_MinCut_Poisson_MINTB)
elseif correlationCoefficient_MinCut_HeavyTail_MINTB > threshold
    fprintf('High dependency of MinCut with MINTB on HeavyTail Graph: r_correlation = %d\n', correlationCoefficient_MinCut_HeavyTail_MINTB)
else
    disp('No significant dependency of MinCut with MINTB on any Graph.')
end

% ---- MinCut (edited )----
plotFeatureComparison(vectorMinCutGridGraphSortedByGamintb, vectorGamintbGridGraphSorted, vectorMinCutPoissonEditedSortedByGamintb, vectorGamintbPoissonEditedSorted, vectorMinCutHeavyTailEditedSortedByGamintb, vectorGamintbHeavyTailEditedSorted, 'MinCut (GAMINTB | edited)', vectorMinCutGridGraphSortedByMintb, vectorMintbGridGraphSorted, vectorMinCutPoissonEditedSortedByMintb, vectorMintbPoissonEditedSorted, vectorMinCutHeavyTailEditedSortedByMintb, vectorMintbHeavyTailEditedSorted, 'MinCut (MINTB | edited)', '/plots/featureMinCut.fig')

% GAMINTB
correlationCoefficient_MinCut_PoissonEdited_GAMINTB = CalculateCorrelationCoefficient(vectorMinCutPoissonEditedSortedByGamintb, vectorGamintbPoissonEditedSorted);
correlationCoefficient_MinCut_HeavyTailEdited_GAMINTB = CalculateCorrelationCoefficient(vectorMinCutHeavyTailEditedSortedByGamintb, vectorGamintbHeavyTailEditedSorted);

if correlationCoefficient_MinCut_PoissonEdited_GAMINTB > threshold
    fprintf('High dependency of MinCut with GAMINTB on PoissonEdited Graph: r_correlation = %d\n', correlationCoefficient_MinCut_PoissonEdited_GAMINTB)
elseif correlationCoefficient_MinCut_HeavyTailEdited_GAMINTB > threshold
    fprintf('High dependency of MinCut with GAMINTB on HeavyTailEdited Graph: r_correlation = %d\n', correlationCoefficient_MinCut_HeavyTailEdited_GAMINTB)
else
    disp('No significant dependency of MinCut with GAMINTB on any Graph.')
end

% MINTB
correlationCoefficient_MinCut_PoissonEdited_MINTB = CalculateCorrelationCoefficient(vectorMinCutPoissonEditedSortedByMintb, vectorMintbPoissonEditedSorted);
correlationCoefficient_MinCut_HeavyTailEdited_MINTB = CalculateCorrelationCoefficient(vectorMinCutHeavyTailEditedSortedByMintb, vectorMintbHeavyTailEditedSorted);

if correlationCoefficient_MinCut_PoissonEdited_MINTB > threshold
    fprintf('High dependency of MinCut with MINTB on PoissonEdited Graph: r_correlation = %d\n', correlationCoefficient_MinCut_PoissonEdited_MINTB)
elseif correlationCoefficient_MinCut_HeavyTailEdited_MINTB > threshold
    fprintf('High dependency of MinCut with MINTB on HeavyTailEdited Graph: r_correlation = %d\n', correlationCoefficient_MinCut_HeavyTailEdited_MINTB)
else
    disp('No significant dependency of MinCut with MINTB on any Graph.')
end