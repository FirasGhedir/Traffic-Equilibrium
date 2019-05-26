% University Ulm
% Algorithm Engineering
% Evaluation

% @author Julian Bestler (julian.bestler@uni-ulm.de)
% @version 1.0

function plotRuntimeComparison(inputVector1, inputVector2, inputVector3, message1, inputVector4, inputVector5, inputVector6, message2)
%Plot comparison of feature vectors

figure;

subplot(1,2,1);
plot(inputVector1, 'b')
hold on
plot(inputVector2, 'g')
hold on
plot(inputVector3, 'r')
legend ('GridGraph','Poisson Graph', 'HeavyTail Graph')
title (message1, 'FontSize', 12)
ylabel('Time in [ms]')
xlabel('Instance')
grid on

subplot(1,2,2);
plot(inputVector4, 'b')
hold on
plot(inputVector5, 'g')
hold on
plot(inputVector6, 'r')
legend ('GridGraph','Poisson Graph', 'HeavyTail Graph')
title (message2, 'FontSize', 12)
ylabel('Time in [ms]')
xlabel('Instance')
grid on
end
