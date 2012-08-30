/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.scratch;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;

import java.util.Arrays;

/**
 * @author Dmitry Kandalov
 */
@State(
		name = "ScratchData",
		storages = {@Storage(id = "main", file = "$APP_CONFIG$/scratch.xml")}
)
public class ScratchData implements PersistentStateComponent<ScratchData> {
	private static final int SIZE = 5;
	private final String scratchTexts[];

	public static ScratchData getInstance() {
		return ServiceManager.getService(ScratchData.class);
	}

	public ScratchData() {
		scratchTexts = new String[SIZE];
		Arrays.fill(scratchTexts, "");
	}

	// used by intellij serializer through reflection
	@SuppressWarnings({"UnusedDeclaration"})
	public String[] getScratchText() {
		return scratchTexts;
	}

	// used by intellij serializer through reflection
	@SuppressWarnings({"UnusedDeclaration"})
	public void setScratchText(String[] text) {
		System.arraycopy(text, 0, scratchTexts, 0, text.length);
	}

	String[] getScratchTextInternal() {
		String result[] = new String[SIZE];
		for (int i = 0; i < result.length; i++) {
			result[i] = XmlUtil.unescape(scratchTexts[i]);
		}
		return result;
	}

	void setScratchTextInternal(int scratchIndex, String text) {
		scratchTexts[scratchIndex] = XmlUtil.escape(text);
	}

	@Override
	public ScratchData getState() {
		return this;
	}

	@Override
	public void loadState(ScratchData scratchData) {
		XmlSerializerUtil.copyBean(scratchData, this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass()) {
			return false;
		} else {
			ScratchData that = (ScratchData) o;
			return Arrays.equals(scratchTexts, that.scratchTexts);
		}
	}

	@Override
	public int hashCode() {
		return scratchTexts == null ? 0 : Arrays.hashCode(scratchTexts);
	}
}