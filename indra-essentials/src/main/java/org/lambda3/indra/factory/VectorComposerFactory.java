package org.lambda3.indra.factory;

/*-
 * ==========================License-Start=============================
 * Indra Core Module
 * --------------------------------------------------------------------
 * Copyright (C) 2016 - 2017 Lambda^3
 * --------------------------------------------------------------------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * ==========================License-End===============================
 */

import com.google.auto.service.AutoService;
import org.lambda3.indra.composition.*;
import org.lambda3.indra.exception.IndraInvalidParameterException;
import org.lambda3.indra.exception.IndraRuntimeException;


@AutoService(IndraFactory.class)
public final class VectorComposerFactory extends CachedIndraFactory<VectorComposer> {

    public VectorComposerFactory() {
        super(VectorComposer.class);
    }

    @Override
    public VectorComposer getDefault() {
        return new AveragedVectorComposer();
    }

    @Override
    public String getName() {
        return IndraFactory.BUILT_IN_FACTORY;
    }

    @Override
    protected VectorComposer create(String name, String... params) throws IndraInvalidParameterException {
        switch (name.toUpperCase()) {
            case "SUM":
                return new SumVectorComposer();
            case "UNIQUESUM":
            case "UNIQUE-SUM":
            case "UNIQUE_SUM":
                return new UniqueSumVectorComposer();
            case "AVERAGE":
                return new AveragedVectorComposer();
            case "L2NORMSUM":
            case "L2NORM-SUM":
            case "L2NORM_SUM":
                return new L2NormSumVectorComposer();
            default:
                throw new IndraRuntimeException("Unsupported vector composer.");
        }
    }
}
