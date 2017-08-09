package org.lambda3.indra.core.vs;

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

import org.lambda3.indra.client.AbstractBasicRequest;
import org.lambda3.indra.client.ModelMetadata;
import org.lambda3.indra.core.IndraCachedFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collection;

public abstract class VectorSpaceFactory extends IndraCachedFactory<VectorSpace, AbstractBasicRequest> implements Closeable {

    @Override
    public VectorSpace create(AbstractBasicRequest request) {
        VectorSpace vectorSpace = super.create(request);
        ModelMetadata metadata = vectorSpace.getMetadata();
        if (request.getApplyStopWords() != null) {
            metadata.applyStopWords(request.getApplyStopWords());
        }

        if (request.getMinWordLength() >= 0) {
            metadata.minWordLength(request.getMinWordLength());
        }

        return vectorSpace;
    }

    public abstract Collection<String> getAvailableModels();

    @Override
    public void close() throws IOException {
        for (String key : this.cache.keySet()) {
            this.cache.get(key).close();
        }

        this.cache.clear();
    }
}