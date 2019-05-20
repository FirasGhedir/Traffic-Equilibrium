% University Ulm
% Algorithm Engineering
% Evaluation
% @author Julian Bestler (julian.bestler@uni-ulm.de)
% @version 1.0

function [r] = plotCorrelationCoefficient(inputVector1, inputVector2, message)
%Makes plot of calculated correlation coefficient

% data
data = [inputVector1, inputVector2]

%make figure
figure;
scatter(data(:,1), data(:,2), '+', 'MarkerFaceColor', 'k');
title (message, 'FontSize', 12)
ylabel('y');
xlabel ('x');
box 'on'
axis square;

% ticks off
set(gca,'Ticklength',[0 0])

% white background
set(gcf,'color','w');

% correlation coefficient
r = corrcoef(data(:, 1), data(:, 2));

disp(r(1,2));

end

