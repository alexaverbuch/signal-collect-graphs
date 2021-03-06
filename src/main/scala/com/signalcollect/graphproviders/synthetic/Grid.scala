/*
 *  @author Philip Stutz
 *  
 *  Copyright 2010 University of Zurich
 *      
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  
 *         http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 */

package com.signalcollect.graphproviders.synthetic

import com.signalcollect.graphproviders.GraphProvider
import com.signalcollect._

class Grid(val width: Int, height: Int) extends GraphProvider[Int] {

  def populate(graph: Graph, vertexBuilder: Int => Vertex[_, _], edgeBuilder: (Int, Int) => Edge[_]) {
    val max = width * height

    for (id <- 1 to max) {
    	graph.addVertex(vertexBuilder(id))
    }
    for (n <- 1 to max) {
      if (n + width <= max) {
        graph.addEdge(n, edgeBuilder(n, n + width))
        graph.addEdge(n + width, edgeBuilder(n + width, n))
      }
      if (n % height != 0) {
        graph.addEdge(n, edgeBuilder(n, n + 1))
        graph.addEdge(n + 1, edgeBuilder(n + 1, n))
      }
    }
  }

}