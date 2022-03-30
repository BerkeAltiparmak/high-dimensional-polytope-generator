//Berke Altiparmak
//16 May 2021

/*
 * Copyright (c) 2021, github.com/Berke6 and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of github.com/Berke6 or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * My algorithm claims that to visualize an n-simplex at any level,
 * it is sufficient to find the coordinates of the spheres of its unit cell.
 * Furthermore, a unit cell of an n-simplex is created by placing a sphere in
 * the nth axis that is equidistant (in the examples of this paper, 1 unit away)
 * to all spheres at the unit cell of the (n-1)-simplex, which creates a recursion
 * that goes all the way to the 0-simplex, which is a dot placed at a origin.
 * The Java program I have written calculates the coordinates of the 
 * unit cell sphere of any n-simplex. Then, by treating the newly 
 * created spheres as Vertices, it repeats the unit cells on these spheres
 * to increase the level count. Thus, the algorithm generates the coordinates
 * of the spheres that construct any n-simplex, and identifies the types of
 * these spheres by examining their elementary coordinates.
 */

/*
 * You can find the research paper on this algorithm with this link:
 * https://docs.google.com/document/d/119WtcDnlALVzvIc7yAiee3pZdfW1Q7CamFsrbjZHfNY/edit?usp=sharing
 * which is written by the owner of this program, github.com/Berke6.
 */

import java.util.*;
import java.lang.*;
import java.util.stream.Collectors;
public class HighDimensionAlgorithm
{
  int n = 0;
  int l = 1;
  List<double[]> spheres;
  List<double[]> unitCellSpheres;
  String[] sphereTypes;
  
  public HighDimensionAlgorithm()
  {
    System.out.println("Welcome to High-Dimension Algorithm, which uses an algorithm to find the coordinates of the vertices that construct the n-simplex you want.");
    
    System.out.println("Enter the dimension you want to observe in your simplex; in other words, enter the n of n-simplex. (Ex: 3)");
    Scanner scn = new Scanner(System.in);
    n = scn.nextInt();
    if(n < 0)
    {
      System.out.println("Enter a non-negative integer for n.");
    }
    
    System.out.println("Enter up to what level you want to observe in your simplex; in other words, enter the l of n-simplex. (Ex: 2, which gives the unit cell)");
    Scanner scl = new Scanner(System.in);
    l = scl.nextInt();
    if(l < 1)
    {
      System.out.println("Enter a positive integer for n.");
    }
    
    
    spheres = new ArrayList<double[]>();
    unitCellSpheres = new ArrayList<double[]>();
    sphereTypes = new String[]{"Vertex", "Edge", "Face", "Center", "Cell"};
    
    //creating the sphere in the zeroth dimension, which is at the origin and has the coordinates of (0, 0, 0,...)
    double[] zerothDimension = new double[n];
    spheres.add(zerothDimension);
    
    algorithm(); //calls the algorithm
    
    removeDuplicates();
    
    //spheres = spheres.stream().distinct().collect(Collectors.toList());
    
    for(int i = 0; i < spheres.size(); i++)
    {
      System.out.print(i + 1 + "th sphere's coordinates: (");
      for(int j = 0; j <= n - 2; j++)
      {
        System.out.print(spheres.get(i)[j] + ", ");
      }
      System.out.print(spheres.get(i)[n - 1] + "). Its elementary coordinates are: (" );//+ elementaryCoordinates(spheres.get(i))
      elementaryCoordinates(spheres.get(i));
    }
    
    
    //if you only want to see the last sphere, put the one above in comments and remove the comments below.
    /*
     System.out.print(n + 1 + "th sphere's coordinates: (");
     for(int j = 0; j <= n - 2; j++)
     {
     System.out.print(spheres.get(n)[j] + ", ");
     }
     System.out.println(spheres.get(n)[n - 1] + ")");
     */
  }
  
  public void algorithm()
  {
    //creating the sphere in the first dimension, which has the coordinates of (0, 1, 0, 0,...)
    double[] firstDimension = new double[n];
    firstDimension[0] = 1;
    spheres.add(firstDimension);
    
    
    //creating the spheres that make up the unit cell
    for(int i = 2; i <= n; i++)
    {
      double[] emptyCoordinates = new double[n];
      spheres.add(emptyCoordinates);
      
      double secondToLastCoordinate = 0;
      double lastCoordinate = 0;
      
      //determining the every coordinate of the unit cell's i-th sphere in the n-th dimension except the last two.
      for (int j = 0; j < i - 2; j++)
      {
        spheres.get(i)[j] = spheres.get(i - 1)[j]; //these coordinates are the same with its predecessor.
      }
      
      //determining the secondToLastCoordinate of the unit cell's i-th sphere in the n-th dimension.
      //The algorithm below places the sphere equidistant to all the other spheres, but it does not have to be 1 unit away
      //from the other spheres because it doesn't use the last axis yet.
      for (int j = 0; j <= i - 1; j++)
      {
        secondToLastCoordinate += spheres.get(i - 1)[j] * spheres.get(i - 1)[j];
      }
      secondToLastCoordinate -= 2*spheres.get(i - 1)[i - 2] * spheres.get(i - 1)[i - 2];
      secondToLastCoordinate /= (-2)*spheres.get(i - 1)[i - 2];
      spheres.get(i)[i - 2] = Math.round(secondToLastCoordinate * 1000.0) / 1000.0; //rounds the secondToLastCoordinate
      
      //determining the last coordinate of the unit cell's i-th sphere in the n-th dimension.
      //The algorithm below uses the last axis so that it carry outs the distance to 1 (from all the other spheres).
      for(int j = 0; j <= i - 1; j++)
      {
        lastCoordinate += spheres.get(i)[j] * spheres.get(i)[j];
      }
      lastCoordinate = Math.sqrt(1 - lastCoordinate);
      spheres.get(i)[i - 1] = Math.round(lastCoordinate * 1000.0) / 1000.0; //rounds the lastCoordinate
    }
    unitCellSpheres.addAll(spheres);
    unitCellSpheres.remove(0); //removing the zeroth cell because it will only slow down the next step (below) because its coordinates are full of zeros.
    
    
    //creating spheres in the other levels:
    for(int i = 3; i <= l; i++)
    {
      int currentSize = spheres.size();
      for(int j = 0; j < currentSize; j++)
      {
        for(int k = 0; k < n; k++)
        {
          spheres.add(addArrays(spheres.get(j), unitCellSpheres.get(k)));
        }
      }
      removeDuplicates();
    }
    
    
  }
  
  public void elementaryCoordinates(double[] currentSphere)
  {
    String coordinates = "(";
    int[] coorArr = new int[n];
    int numberOfAxis = 0;
    
    for(int i = 1; i <= n; i++)
    {
      while(currentSphere[n-i] > 0)
      {
        currentSphere = subtractArrays(currentSphere, unitCellSpheres.get(n-i));
        coorArr[n-i]++;
      }
    }
    
    for(int i = 0; i < coorArr.length; i++)
    {
      if(i == coorArr.length - 1)
      {
      System.out.print(coorArr[i] + "). ");
      }
      else
      {
      System.out.print(coorArr[i] + ", ");
      }
      if(coorArr[i] > 0)
      {
        numberOfAxis++;
      }
    }
    
    try
    {
    System.out.println("This means that the sphere is of type " + sphereTypes[numberOfAxis]);
    }
    catch(Exception e)
    {
    System.out.println("This means that the sphere is of type " + numberOfAxis + "-faces");
    }
      
  }
  
  public double[] addArrays(double[] first, double[] second) 
  {
    double[] result = new double[first.length]; 
    for (int i = 0; i < first.length; i++) 
    { 
      result[i] = Math.round((first[i] + second[i]) * 1000.0) / 1000.0; 
    } 
    return result; 
  }
  
  public double[] subtractArrays(double[] first, double[] second) 
  {
    double[] result = new double[first.length]; 
    for (int i = 0; i < first.length; i++) 
    { 
      result[i] = Math.round((first[i] - second[i]) * 1000.0) / 1000.0; 
    } 
    return result; 
  }
  
  public void removeDuplicates()
  {
    for(int i = 0; i < spheres.size() - 1; i++)
    {
      for(int j = i + 1; j < spheres.size(); j++)
      {
        if(Arrays.equals(spheres.get(i), spheres.get(j)))
        {
          spheres.remove(j);
        }
      }
    }
  }
  
  public static void main(String[] arg)
  {
    HighDimensionAlgorithm hda = new HighDimensionAlgorithm();
  }
}