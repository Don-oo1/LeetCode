# 42. Trapping Rain Water

Given `n` non-negative integers representing an elevation map where the width of each bar is `1`, compute how much water it can trap after raining.

**Example 1:**

![](https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png)

```
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

```

**Example 2:**

```
Input: height = [4,2,0,3,2,5]
Output: 9

```

**Constraints:**

- `n == height.length`
- `1 <= n <= 2 * 104`
- `0 <= height[i] <= 105`

---

## Assumptions:

1. water will trap between two building.
2. water will trap on top of building.
3. water quntity is caculate by ( width  * height  ) of water
4. width of every building is one 

# Approach 1:

1. we will calculate amount of water on top of each building
2. so width of water is same as width of building which is 1
3. now we need to find height of water pf each building
    1. water hight id same as minimum height of both edge building 
    2. end border is allways biggest building of both side ]
    3. than we need to subtrac height of current building from height of water because water is above the current building
    4. so formula look, min( leftBiggestBuildingHeight, rightBiggestBuildingHeight ) -  currentNuildingHeight 
    5. if minimum height of edge building same or less than  current building than water no water will trap because no edge will available one side
        1. to handle this we will include current building height also when we find max height building  in right side and left side
        2. so if current buiding is max or any side than minimum than other side in this case accodeing to over formula . will return 0 water count. 
    6. to find biggest for both side we will run loop 0 to i for left side (including current building ) and i to lengt-1(including current building) for right side .

## Code

```java
public int trap(int[] height) {
    int[] leftBiggest = getLeftBiggestArray(height);
    int[] rightBiggest = getRightBiggestArray(height);

    int currentWater,waterCount = 0;
    int leftBiggetBuilding = 0, rightBiggestBuilding = 0 ;
    for( int i = 0 ; i < height.length ; ++i ){
    
		    
		    leftBiggetBuilding = height[i]; // current building include
		    
		    for(int j = 0; j < i ;++j){
			    leftBiggetBuilding = max( leftBiggetBuilding, height[j] );
			  }
		    
		    rightBiggestBuilding = height[i]; // current building include
		    
		    for(int j = i+1; j < height.length ;++j){
			    rightBiggestBuilding = max( rightBiggestBuilding, height[j] );
			  }
        currentWater = Math.min( leftBiggest, rightBiggest ) - height[i];
        waterCount += currentWater;

    }

    return waterCount;

}

```

## Time Complexity

 

- there is inner loop is running so TC = O(n^2)

## Space Complexity

- only constant number of space use so SC = O(1)

# Approach 1.2

- we get O(n^2) TC because we find maxbuiding height for both side by running loop
- we can pre store max left and right height of building for any building in array
- for find left max building
    - we will create array named leftBiggestBuilding of size of height
    - left side biggest building for first building is it self because no building available left side of it
        - here we consider current building also
    - for all other building we can use this formula : Max( height[i], biggestBuilding[i-1] )
- for find right max building
    - same as finding left side but
    - last bulling’s biggestRightBuilding  is it self because not building available right side
    - loop start from height.length-2 index to 0
    - formula : Max( height[i], biggestBuilding[i+1] )
- nowe can find max building of right and left side in constant time

## Code

```java
class Solution {
    public int trap(int[] height) {

        int[] leftBiggest = getLeftBiggestArray(height);
        int[] rightBiggest = getRightBiggestArray(height); 

        int currentWater,waterCount = 0;

        for( int i = 0 ; i < height.length ; ++i ){

            currentWater = Math.min( leftBiggest[i], rightBiggest[i] ) - height[i];
            waterCount += currentWater;

            
        }

        return waterCount;
        
    }

    int[] getLeftBiggestArray(int[] height){

        int[] leftBiggest = new int[height.length];

        leftBiggest[0] = height[0];

        for(int i = 1 ; i < leftBiggest.length ; ++i){

            leftBiggest[i] = Math.max( leftBiggest[i-1], height[i] );
        }
        return leftBiggest;
    }

    
    int[] getRightBiggestArray(int[] height){

        int[] rightBiggest = new int[height.length];

        rightBiggest[ rightBiggest.length-1 ] = height[ height.length-1 ];

        for(int i = rightBiggest.length-2 ; i >= 0 ; --i){

            rightBiggest[i] = Math.max( rightBiggest[i+1], height[i] );
        }
        return rightBiggest;
    }
}
```

## Time Complexity

- there is no inner loop so TC = O(n)

## Space Complexity

- there we use two array of size N so SC = O(n)