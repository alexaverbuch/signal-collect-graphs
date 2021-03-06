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

class Star(val vertices: Int, symmetric: Boolean = false) extends GraphProvider[Int] with Traversable[(Int, Int)] {

  def populate(graph: Graph, vertexBuilder: Int => Vertex[_, _], edgeBuilder: (Int, Int) => Edge[_]) {
    graph.addVertex(vertexBuilder(0))
    for (i <- 1 to vertices) {
      graph.addVertex(vertexBuilder(i))
      graph.addEdge(i, edgeBuilder(i, 0))
      if (symmetric)
        graph.addEdge(0, edgeBuilder(0, i))
    }
  }
  
  def foreach[U](f: ((Int, Int)) => U) = {
    for (i <- 1 to vertices) {
      f((i, 0))
      if (symmetric)
        f((0, i))
    }
  }
}