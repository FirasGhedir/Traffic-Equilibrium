% University Ulm
% Algorithm Engineering
% Evaluation
% @author Julian Bestler (julian.bestler@uni-ulm.de)
% @version 1.0

function output = CalculateCorrelationCoefficient(inputVector1, inputVector2)
% The following is a function that takes two equal sized vectors 
 % inputVector1 and inputVector2, and is supposed to return a vector 
 % containing single correlation coefficients for image correspondence
 
meaninputVector1 = mean(inputVector1);
meaninputVector2 = mean(inputVector2);
stdinputVector1 = std(inputVector1);
stdinputVector2 = std(inputVector2);

for i = 1:1:length(inputVector1)
    
    inputVector1(i) = (inputVector1(i) - meaninputVector1)/stdinputVector1;
    inputVector2(i) = (inputVector2(i) - meaninputVector2)/stdinputVector2;
    mult = inputVector1(i) * inputVector2(i);
    
end

output = sum(mult)/(length(inputVector1)-1);

end