<template>
  <div id="birthday-list">
    <span class="alert-error">{{ this.errorMessage }}</span>
    <span class="alert-success">{{ this.successMessage }}</span>
    <input
      type="date"
      class="birthday-date"
      v-model="birthdayDate"
      @keyup.enter="saveBirthday">
    <button @click="saveBirthday" type="button">{{ $t('birthdayApp.i18n.OK') }}</button>
    <ul>
      <li v-for="(item, index) in birthdayItems" :key="index">
        <div v-if="item.birthday">
          <a :href="userProfileURL(item.userName)">
            <img
              width="25"
              height="25"
              :src="item.avatar"
              :alt="`Avatar of ${item.fullName}`">
            <span :id="'item-' + index">{{ item.fullName }}</span>
          </a>
          <span :id="'item-' + index">{{ item.birthday }}</span>
        </div>
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  data: () => ({
    birthdayItems: [],
    successMessage: '',
    errorMessage: ''
  }),
  created() {
    return fetch(`${eXo.env.portal.context}/${eXo.env.portal.rest}/connections/birthday`, {
      method: 'GET',
      credentials: 'include',
    }).then(resp => {
      if (!resp || !resp.ok) {
        throw new Error('Response code indicates a server error', resp);
      } else {
        return resp.json();
      }
    }).then(data => {
      this.birthdayItems = data;
    });
  },
  methods: {
    saveBirthday() {
      return fetch(`${eXo.env.portal.context}/${eXo.env.portal.rest}/connections/birthday`, {
        method: 'POST',
        credentials: 'include',
        body: `birthday=${this.birthdayDate}&userName=${eXo.env.portal.userName}`,
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(resp => {
        if (!resp || !resp.ok) {
          this.errorMessage = 'An error happened when updating birthday';
          setTimeout(() => (this.errorMessage = ''), 5000);
          throw new Error('Response code indicates a server error', resp);
        } else {
          return resp.json();
        }
      }).then(() => {
        this.successMessage = 'Birthday updated successfully!';
        setTimeout(() => (this.successMessage = ''), 5000);
      });
    },
    userProfileURL(userName) {
      return userName && `/portal/dw/profile/${userName}` || '#';
    }
  }
};

</script>
