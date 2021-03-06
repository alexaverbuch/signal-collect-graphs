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

import com.signalcollect._
import com.signalcollect.graphproviders.GraphProvider

class FullyConnected(val vertices: Int) extends GraphProvider[Int] {

  def populate(graph: Graph, vertexBuilder: Int => Vertex[_, _], edgeBuilder: (Int, Int) => Edge[_]) {
    for (id <- (0 to vertices).par) {
      graph.addVertex(vertexBuilder(id))
    }
    for (i <- 0 to vertices) {
      for (j <- 0 until i) {
        graph.addEdge(i, edgeBuilder(i, j))
        graph.addEdge(j, edgeBuilder(j, i))
      }
    }
  }

}