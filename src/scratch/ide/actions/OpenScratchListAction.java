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
package scratch.ide.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import scratch.ide.ScratchComponent;

import static scratch.ide.Util.holdingTo;
import static scratch.ide.actions.OpenDefaultScratchAction.projectFor;

/**
 * @author Dmitry Kandalov
 */
public class OpenScratchListAction extends DumbAwareAction {

	@Override
	public void actionPerformed(AnActionEvent event) {
		ScratchComponent.instance().userWantsToSeeScratchesList(holdingTo(event));
	}

	@Override
	public void update(AnActionEvent event) {
		event.getPresentation().setEnabled(projectFor(event) != null);
	}
}