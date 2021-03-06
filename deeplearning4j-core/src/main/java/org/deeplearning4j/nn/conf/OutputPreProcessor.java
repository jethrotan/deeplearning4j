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

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * Output pre processor's handle layer to layer interactions
 * to ensure things like proper shape for input among other things.
 *
 * @author Adam Gibson
 */
public interface OutputPreProcessor {
    /**
     * Used for handling pre processing of layer output.
     * The typical use case is for handling reshaping of output
     * in to shapes proper for the next layer of input.
     * @param output the output to pre process
     * @return the pre processed output
     */
    INDArray preProcess(INDArray output);

}
