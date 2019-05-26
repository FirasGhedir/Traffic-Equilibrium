% University Ulm
% Algorithm Engineering
% Evaluation
% @author Julian Bestler (julian.bestler@uniulm.de)
% @version 1.0

function [t, df] = t_testTwoSample(gv1, gv2, message)
% Inputs: gv1 = given sample 1
%         gv2 = given sample 2
% Outputs: t = t-test
%          df = degrees of freedom 

% number of elements in vector 1
r1 = length(gv1);
% sum of elements in vector 1
v1 = sum(gv1);
% sum of squared elements in vector 1
d1 = sum(gv1.^2);
% mean of vector 1
m1 = mean(gv1); 

% number of elements in vector 2
r2 = length(gv2);
% sum of elements in vector 2
v2 = sum(gv2);
% sum of squared elements in vector 2
d2 = sum(gv2.^2);
% mean of vector 2
m2 = mean(gv2); 

% calculation of t-test and degrees of freedom
% Case 2: two samples, equal mean and std. dev.
av1 = (d1 - v1^2 / r1) /(r1 - 1);
av2 = (d2 - v2^2 / r2) /(r2 - 1);
av3 = (m1 - m2) / sqrt(1/r1 + 1/r2);
df = r1 + r2 - 2;
t = av3 / sqrt(((r1-1)*av1 + (r2-1)*av2) / df); 

%Normal Probability Plot
% figure;
% normplot(t)
% title (message, 'FontSize', 12)
% ylabel('probability')
% xlabel('t-value')
% grid on

end

