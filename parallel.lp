\ENCODING=ISO-8859-1
\Problem name: ilog.cplex

Minimize
 obj: x1 + x2 + x3 + x4
Subject To
 c1: x1 + x2 >= 1
 c2: x3 + x4 >= 2
 c3: x1 + 2 x2 + 3 x3 + 4 x4  = 8
Bounds
 0 <= x1 <= 1
 0 <= x2 <= 1
 0 <= x3 <= 1
 0 <= x4 <= 1
End
