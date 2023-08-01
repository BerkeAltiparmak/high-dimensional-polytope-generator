# High Dimensional Polytope Generator

The pursuit to understand higher dimensions has been of attraction to mathematicians and physicists for decades, yet the lack of a generalized algorithm to visualize higher dimensional objects is a simple proof of how limited our knowledge within this field is. 

My research paper, which can be accessed on https://www.researchgate.net/publication/359578526_An_Algorithm_To_Visualize_High_Dimensional_n-Simplexes_and_What_It_Sheds_Light_On, though, might be the start of something big. In this paper, an algorithm that can be used to visualize and generate the simplest high-dimensional polytopes, known as simplexes (“n” indicates the level of dimension, and “simplex” is the generalization of the notion of a triangle to arbitrary dimensions), will be introduced. To construct this algorithm, patterns among the polytopes that are observable by the human eye (0-simplex, 1-simplex, 2-simplex, and 3-simplex) will be observed to derive general conclusions regarding the structure of n-simplexes. By using the aforementioned algorithm, the final aim of the paper is to analyze the properties of the n-simplexes as the dimension of the simplexes gets infinitely large, or in other words, as n goes to infinity.

This project takes n, the dimension of the simplex, and l, the level of the simplex, as inputs to generate the coordinates of the vertices that can be used to construct an n-simplex at the l level. It also specifies the type of each vertex (Vertex, Edge, Face, etc.).

<img width="489" alt="Screen Shot 2022-10-02 at 23 29 23" src="https://user-images.githubusercontent.com/96665962/193496598-5326242b-94d0-49d1-86fa-74127c2fec74.png">

<br />
<br />
<br />

Here are the first four simplexes:

<img width="1321" alt="Screen Shot 2023-08-01 at 16 42 24" src="https://github.com/BerkeAltiparmak/high-dimensional-polytope-generator/assets/96665962/4efc5abe-9aae-4e21-a3d2-3e10c2d36f99">

<br />
<br />

This is a cut from 4-Simplex, generated through the proposed algorithm:

<img width="403" alt="Screen Shot 2022-10-02 at 23 27 35" src="https://user-images.githubusercontent.com/96665962/193496608-b16c7bbb-16c2-45d6-b244-6ee7f708ec03.png">
