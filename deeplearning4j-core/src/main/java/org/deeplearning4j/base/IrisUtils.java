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

package org.deeplearning4j.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.core.io.ClassPathResource;

public class IrisUtils {

    public static List<DataSet> loadIris(int from,int to) throws IOException {
        ClassPathResource resource = new ClassPathResource("/iris.dat");
        @SuppressWarnings("unchecked")
        List<String> lines = IOUtils.readLines(resource.getInputStream());
        List<DataSet> list = new ArrayList<>();
        INDArray ret = Nd4j.ones(Math.abs(to - from), 4);
        List<String> outcomeTypes = new ArrayList<>();
        double[][] outcomes = new double[lines.size()][3];
        int putCount = 0;
        for(int i = from; i < to; i++) {
            String line = lines.get(i);
            String[] split = line.split(",");

            addRow(ret,putCount++,split);

            String outcome = split[split.length - 1];
            if(!outcomeTypes.contains(outcome))
                outcomeTypes.add(outcome);
            double[] rowOutcome = new double[3];
            rowOutcome[outcomeTypes.indexOf(outcome)] = 1;
            outcomes[i] = rowOutcome;
        }

        for(int i = 0; i < ret.rows(); i++)
            list.add(new DataSet(ret.getRow(i), Nd4j.create(outcomes[i])));

        return list;
    }

    private static void addRow(INDArray ret,int row,String[] line) {
        double[] vector = new double[4];
        for(int i = 0; i < 4; i++)
            vector[i] = Double.parseDouble(line[i]);

        ret.putRow(row,Nd4j.create(vector));
    }
}
