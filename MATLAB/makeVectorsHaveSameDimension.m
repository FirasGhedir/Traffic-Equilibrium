% University Ulm
% Algorithm Engineering
% Evaluation
% @author Julian Bestler (julian.bestler@uni-ulm.de)
% @version 1.0

function [outputVector1,outputVector2] = makeVectorsHaveSameDimension(inputVector1,inputVector2)
%This function handles two vectors so that they all have the 
%   same dimension

size1 = length(inputVector1);
size2 = length(inputVector2);

if size1 < size2
            tmp1 = zeros(1,(size2-size1));
            tmp2 = transpose(inputVector1);
            outputVector1 = [tmp1 tmp2];
            outputVector2 = inputVector2;
end
if size2 < size1
            tmp1 = zeros(1,(size1-size2));
            tmp2 = transpose(inputVector2);
            outputVector1 = inputVector1;
            outputVector2 = [tmp1 tmp2];
end            
if size2 == size1
            outputVector1 = inputVector1;
            outputVector2 = inputVector2;
end

