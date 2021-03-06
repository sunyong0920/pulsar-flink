/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.streaming.connectors.pulsar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Data for various test cases.
 */
public class SchemaData {

    public static final List<Boolean> BOOLEAN_LIST = Arrays.asList(true, false, true, true, false);
    public static final List<Integer> INTEGER_LIST = Arrays.asList(1, 2, 3, 4, 5);
    public static final List<byte[]> BYTES_LIST = INTEGER_LIST.stream().map(i -> i.toString().getBytes()).collect(Collectors.toList());
    public static final List<Byte> INT_8_LIST = INTEGER_LIST.stream().map(Integer::byteValue).collect(Collectors.toList());
    public static final List<Short> INT_16_LIST = INTEGER_LIST.stream().map(Integer::shortValue).collect(Collectors.toList());
    public static final List<Long> INT_64_LIST = INTEGER_LIST.stream().map(Integer::longValue).collect(Collectors.toList());
    public static final List<Double> DOUBLE_LIST = INTEGER_LIST.stream().map(Integer::doubleValue).collect(Collectors.toList());
    public static final List<Float> FLOAT_LIST = INTEGER_LIST.stream().map(Integer::floatValue).collect(Collectors.toList());
    public static final List<String> STRING_LIST = INTEGER_LIST.stream().map(Objects::toString).collect(Collectors.toList());
    public static List<Date> dateList;
    public static List<Timestamp> timestampList;
    public static List<Foo> fooList;

    static {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        dateList = INTEGER_LIST.stream().map(i -> {
            cal.set(2019, 0, i);
            return cal.getTime();
        }).collect(Collectors.toList());

        cal.clear();
        timestampList = INTEGER_LIST.stream().map(i -> {
            cal.set(2019, 0, i, 20, 35, 40);
            return new Timestamp(cal.getTimeInMillis());
        }).collect(Collectors.toList());

        fooList = Arrays.asList(
                new Foo(1, 1.0f, new Bar(true, "a")),
                new Foo(2, 2.0f, new Bar(false, "b")),
                new Foo(3, 0, null),
                new Foo(0, 0, null));
    }

    /**
     * Foo type.
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Foo {
        public int i;
        public float f;
        public Bar bar;

        @Override
        public String toString() {
            return "" + i + "," + f + "," + (bar == null ? "null" : bar.toString());
        }
    }

    /**
     * Bar type.
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Bar {
        public boolean b;
        public String s;

        @Override
        public String toString() {
            return "" + b + "," + s;
        }
    }
}
