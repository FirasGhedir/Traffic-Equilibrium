% University Ulm
% Algorithm Engineering
% Evaluation
% @author Julian Bestler (julian.bestler@uniulm.de)
% @version 1.0


function [t, df] = t_testOneSample(gv, gm, message)
% Inputs: gv = given vector
%         gm = assumed mean
% Outputs: t = t-test
%          df = degrees of freedom 

% number of elements in vector
r = length(gv);
% sum of elements
v = sum(gv);
% sum of squared elements
d = sum(gv.^2);
% mean of current vector
m = mean(gv); 

% calculation of t-test and degrees of freedom
% Case 1: one vector with assumed mean
av = (d - v^2 / r) / (r - 1);
t = (m - gm) * sqrt(r/av);
df = r - 1;

%Normal Probability Plot
figure;
normplot(t)
title (message, 'FontSize', 12)
ylabel('probability')
xlabel('t-value')
grid on

end

