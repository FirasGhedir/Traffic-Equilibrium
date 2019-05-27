% University Ulm
% Algorithm Engineering
% Evaluation

% @author Julian Bestler (julian.bestler@uni-ulm.de)
% @version 1.0

function plotFeatureComparison(inputVector1, inputVector2, inputVector3, inputVector4, inputVector5, inputVector6, message1, inputVector7, inputVector8, inputVector9, inputVector10, inputVector11, inputVector12, message2, filename)
%Plot comparison of feature vectors

figure;

%% left side
subplot(3,2,1);
scatter(inputVector2, inputVector1, 'b.')
lsline
title (message1, 'FontSize', 12)
legend ('GridGraph')
ylabel('Value')
xlabel('Number of tollbooths')
hold on
subplot(3,2,3);
scatter(inputVector4, inputVector3, 'g.')
lsline
legend ('Poisson Graph')
ylabel('Value')
xlabel('Number of tollbooths')
hold on
subplot(3,2,5);
scatter(inputVector6, inputVector5, 'r.')
lsline
legend ('HeavyTail Graph')
ylabel('Value')
xlabel('Number of tollbooths')

%% right side
subplot(3,2,2);
scatter(inputVector8, inputVector7, 'b.')
lsline
title (message2, 'FontSize', 12)
legend ('GridGraph')
ylabel('Value')
xlabel('Number of Tollbooths')
hold on
subplot(3,2,4);
scatter(inputVector10, inputVector9, 'g.')
lsline
legend ('Poisson Graph')
ylabel('Value')
xlabel('Number of Tollbooths')
hold on
subplot(3,2,6);
scatter(inputVector12, inputVector11, 'r.')
lsline
legend ('HeavyTail Graph')
ylabel('Value')
xlabel('Number of Tollbooths')
end
