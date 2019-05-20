% University Ulm
% Algorithm Engineering
% Evaluation
% @author Julian Bestler (julian.bestler@uni-ulm.de)
% @version 1.0

function plotFeatureComparison(inputVector1,inputVector2, message)
%Plot comparison of feature vectors
figure;
plot(inputVector1./inputVector2 , 'r')
legend (message)
title (message, 'FontSize', 12)
ylabel('Value')
xlabel('Instance')
grid on
end

