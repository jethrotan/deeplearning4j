/*
 * Copyright 2015 Skymind,Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.deeplearning4j.nn.conf;

import static org.junit.Assert.*;


import org.deeplearning4j.models.featuredetectors.rbm.RBM;
import org.deeplearning4j.nn.layers.factory.LayerFactories;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.junit.Test;
import org.nd4j.linalg.factory.Nd4j;

/**
 * Created by agibsonccc on 11/27/14.
 */
public class NeuralNetConfigurationTest {
    @Test
    public void testJson() {
        NeuralNetConfiguration conf = new NeuralNetConfiguration.Builder().iterationListener(new ScoreIterationListener(10))
                .dist(Nd4j.getDistributions().createNormal(1,1))
                .layerFactory(LayerFactories.getFactory(RBM.class))
                .build();
        String json = conf.toJson();
        NeuralNetConfiguration read = NeuralNetConfiguration.fromJson(json);
        assertEquals(conf,read);
    }


}
